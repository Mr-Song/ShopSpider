package com.fatlamb.fattt.controller;

import com.fatlamb.fattt.entity.GoodsListItemInfo;
import com.fatlamb.fattt.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        long sorttime = 0 ;
        List<GoodsListItemInfo> infos = indexService.getListByPage(sorttime , 20);
        model.addAttribute("itemlist" , infos);
        return "index";
    }
}
