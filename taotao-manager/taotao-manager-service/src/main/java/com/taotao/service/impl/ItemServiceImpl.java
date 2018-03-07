package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        // 分页处理
        PageHelper.startPage(page, rows);
        // 执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);
        // 创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        // 取总记录数
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
