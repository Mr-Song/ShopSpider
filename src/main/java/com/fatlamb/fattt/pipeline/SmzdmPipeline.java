package com.fatlamb.fattt.pipeline;

import com.fatlamb.fattt.constant.Constants;
import com.fatlamb.fattt.dao.DBHelper;
import com.fatlamb.fattt.dao.FlowItemDao;
import com.fatlamb.fattt.dao.SmzdmEditorDao;
import com.fatlamb.fattt.entity.FlowItem;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import com.fatlamb.fattt.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.util.List;

/**
 * Created by 58 on 2016/12/30.
 */
public class SmzdmPipeline implements Pipeline {

    private PictureService pictureService = PictureService.getInstance();

    public void process(ResultItems resultItems, Task task) {
        List<GoodsListItemInfo> goodsListInfo = resultItems.get("itemList");
        SmzdmEditorDao zdmdao = DBHelper.getMapper(SmzdmEditorDao.class);
        FlowItemDao flowDao = DBHelper.getMapper(FlowItemDao.class);

        for (GoodsListItemInfo item : goodsListInfo){
            try{
                if (item.getArticle_timesort() == null){
                    continue;
                }
                GoodsListItemInfo info = zdmdao.getItemById(item.getArticle_id());
                if(info == null){
                    System.out.print("insert"+ item.getArticle_id());
                    //处理图片地址
                    String fileName = pictureService.getPictureByUrl(item.getArticle_pic() , Constants.Source.SMZDM);
                    if(fileName != null){
                        String path = "../resources/static/pic/" +Constants.Source.SMZDM + File.separator+ fileName;
                        item.setArticle_pic(path);
                    }
                    zdmdao.insert(item);
                    FlowItem flowItem = SmzdmTransfer.transfer(item);
                    flowDao.insert(flowItem);

                }else {
                    System.out.print("update:" + info.getArticle_id());
                    zdmdao.updateCommentInfo(item);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    }
}
