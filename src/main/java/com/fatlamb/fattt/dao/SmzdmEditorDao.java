package com.fatlamb.fattt.dao;

import com.fatlamb.fattt.entity.GoodsListItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hasee on 2016/12/31.
 */
public interface SmzdmEditorDao {
    public void insert(GoodsListItemInfo item);

    /**
     * 更新条目的评论，值否，收藏等信息
     * @param item
     */
    public void updateCommentInfo(GoodsListItemInfo item);

    public List<GoodsListItemInfo> getListByPage(@Param("pageSize") int pagesSize , @Param("sortTime") int sortTime);

    public GoodsListItemInfo getItemById(@Param("article_id")String id);
}
