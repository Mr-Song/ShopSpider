package com.fatlamb.fattt.dao;

import com.fatlamb.fattt.entity.FlowItem;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hasee on 2017/1/7.
 */
public interface FlowItemDao {
    public void insert(FlowItem item);

    public List<FlowItem> getListByPage(@Param("pageSize") int pagesSize , @Param("id") long id);

    public GoodsListItemInfo getItemById(@Param("id") String id);
}
