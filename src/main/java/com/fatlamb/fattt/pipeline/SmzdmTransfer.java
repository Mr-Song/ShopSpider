package com.fatlamb.fattt.pipeline;

import com.fatlamb.fattt.constant.Constants;
import com.fatlamb.fattt.entity.FlowItem;
import com.fatlamb.fattt.entity.GoodsListItemInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Created by hasee on 2017/1/12.
 */
public class SmzdmTransfer {
    public static FlowItem transfer(GoodsListItemInfo item){
        FlowItem flowItem = new FlowItem();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        flowItem.setAddtime(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        flowItem.setDetailid(Long.parseLong(item.getArticle_id()));
        flowItem.setDirectbuyurl("");
        flowItem.setHeadpic(item.getArticle_pic());
        flowItem.setMall(item.getArticle_mall());
        flowItem.setPricedesc(item.getArticle_price());
        flowItem.setSource(Constants.Source.SMZDM_CN);
        flowItem.setTargeturl(item.getArticle_url());
        flowItem.setTitle(item.getArticle_title());
        flowItem.setSummary("这是一个很值得买的东西，别想了，快剁手吧！！！");
        flowItem.setReadcount(100);
        return flowItem;
    }
}
