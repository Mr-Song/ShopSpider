package com.fatlamb.fattt.service;

import com.fatlamb.fattt.dao.DBHelper;
import com.fatlamb.fattt.dao.SmzdmEditorDao;
import com.fatlamb.fattt.entity.GoodsListItemInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 58 on 2017/1/4.
 */
@Component
public class IndexService {
    public List<GoodsListItemInfo> getListByPage(long sorttime , int pagesize){
        SmzdmEditorDao dao = DBHelper.getMapper(SmzdmEditorDao.class);
        return dao.getListByPage(pagesize , sorttime);
    }
}
