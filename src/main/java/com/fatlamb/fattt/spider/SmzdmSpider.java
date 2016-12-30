package com.fatlamb.fattt.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fatlamb.fattt.pipeline.DbPipeline;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 58 on 2016/12/29.
 */
public class SmzdmSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(2).setSleepTime(1000);

    public void process(Page page) {
        JSONObject json = JSONObject.parseObject(page.getJson().get());
        String error_code = json.getString("error_code");
        if(!"0".equals(error_code)){
            return ;
        }
        List<GoodsListItemInfo> itemInfoList = new ArrayList<GoodsListItemInfo>();
        String rows = json.getJSONObject("data").getJSONArray("rows").toJSONString();
        itemInfoList = JSON.parseArray(rows , GoodsListItemInfo.class);
        System.out.println(itemInfoList.get(0).getArticle_id());
        page.putField("itemList" , itemInfoList);
        String url = page.getUrl().toString();
        int index = url.lastIndexOf("=");
        int pagenum = Integer.parseInt(url.substring(index+1));
        String timesort = itemInfoList.get(itemInfoList.size()-1).getTime_sort();
        if(pagenum < 3){
            String target = url.substring(0 , index+1) + (pagenum+1);
            System.out.println(target);
            target = target.replaceAll("time_sort=.*?&" , "time_sort="+timesort+"&");
            page.addTargetRequest(target);

        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new SmzdmSpider())
                .addUrl("https://api.smzdm.com/v1/util/editors_recommend?channel_id=18&device_id=&smzdm_id=&limit=20&time_sort=&f=android&s=&v=360&weixin=1&page=1")
                .addPipeline(new DbPipeline())
                .thread(1)
                .run();
    }
}
