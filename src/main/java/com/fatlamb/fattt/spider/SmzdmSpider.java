package com.fatlamb.fattt.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by 58 on 2016/12/29.
 */
public class SmzdmSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(2).setSleepTime(1000);

    public void process(Page page) {
        System.out.println(page.getUrl());
        System.out.println(page.getHtml());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new SmzdmSpider()).addUrl("https://api.smzdm.com/v1/util/editors_recommend?channel_id=18&device_id=&smzdm_id=&page=1&limit=20&time_sort=&f=android&s=&v=360&weixin=1").thread(1).run();
    }
}
