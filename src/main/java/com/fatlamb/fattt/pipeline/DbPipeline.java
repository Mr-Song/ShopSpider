package com.fatlamb.fattt.pipeline;

import com.fatlamb.fattt.dao.DBHelper;
import com.fatlamb.fattt.dao.SmzdmEditorDao;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by 58 on 2016/12/30.
 */
public class DbPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        List<GoodsListItemInfo> goodsListInfo = resultItems.get("itemList");
        SmzdmEditorDao dao = DBHelper.getMapper(SmzdmEditorDao.class);
        for (GoodsListItemInfo item : goodsListInfo){
            try{
                if (item.getArticle_timesort() == null){
                    continue;
                }
                GoodsListItemInfo info = dao.getItemById(item.getArticle_id());
                if(info == null){
                    System.out.print("insert"+ item.getArticle_id());
                    dao.insert(item);
                }else {
                    System.out.print("update:" + info.getArticle_id());
                    dao.updateCommentInfo(item);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    }
}
