package com.fatlamb.fattt.timetask;

import com.fatlamb.fattt.spider.ISpider;
import com.fatlamb.fattt.spider.SmzdmSpider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by 58 on 2017/1/6.
 */
public class SpiderTask {
    private static List<ISpider> spiders = new ArrayList<ISpider>();
    static {
        spiders.add(new SmzdmSpider());
    }

    public static void init(){
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                for (ISpider spider : spiders){
                    spider.start();
                }
            }
        } , 0 , 1,TimeUnit.HOURS);
    }
}
