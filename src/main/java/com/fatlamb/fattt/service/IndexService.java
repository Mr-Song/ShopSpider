package com.fatlamb.fattt.service;

import com.fatlamb.fattt.dao.DBHelper;
import com.fatlamb.fattt.dao.FlowItemDao;
import com.fatlamb.fattt.dao.SmzdmEditorDao;
import com.fatlamb.fattt.entity.FlowItem;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 58 on 2017/1/4.
 */
@Component
public class IndexService {
    public List<FlowItem> getListByPage(long id , int pagesize){
        FlowItemDao dao = DBHelper.getMapper(FlowItemDao.class);
        return dao.getListByPage(pagesize , id);
    }
}
