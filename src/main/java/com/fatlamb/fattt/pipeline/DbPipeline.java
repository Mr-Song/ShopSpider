package com.fatlamb.fattt.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by 58 on 2016/12/30.
 */
public class DbPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.get("itemList"));
    }
}
