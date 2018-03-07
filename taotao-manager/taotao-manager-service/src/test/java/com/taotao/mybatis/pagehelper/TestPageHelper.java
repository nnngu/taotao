package com.taotao.mybatis.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {
    @Test
    public void testPageHelper() {
        // 初始化Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        // 从容器中获得mapper代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        // 执行查询
        TbItemExample example = new TbItemExample();
        // 查询之前进行分页处理
        PageHelper.startPage(1, 30); // 注意，分页插件仅对最近的这一次查询有效，下一次查询就无效了。怎么实现的呢？使用了LocalThread。
        List<TbItem> list = itemMapper.selectByExample(example);
        // 取分页信息
        System.out.println("结果集中的记录数：" + list.size());
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
    }
}
