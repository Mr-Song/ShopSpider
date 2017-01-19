package com.fatlamb.fattt.controller;

import com.fatlamb.fattt.entity.FlowItem;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import com.fatlamb.fattt.service.IndexService;
import com.fatlamb.fattt.vo.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by 58 on 2017/1/3.
 */

@Controller
public class IndexController {

    @Autowired
    IndexService indexService ;

    @RequestMapping(value = "/" , method = GET)
    public String index(Model model){
        long id = 0 ;
        id = id == 0 ? Long.MAX_VALUE : id;
        List<FlowItem> infos = indexService.getListByPage(id , 20);
        model.addAttribute("itemlist" , infos);
        return "index";
    }

    //spring4  ContentNegotiatingViewResolver
    @ResponseBody
    @RequestMapping(value = "/nextpage" , method = GET)
    public BaseVo nextpage(Model model){
        long id = 0 ;
        id = id == 0 ? Long.MAX_VALUE : id;
        List<FlowItem> infos = indexService.getListByPage(id , 20);
        return BaseVo.success(infos);
    }
}
