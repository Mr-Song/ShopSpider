package com.fatlamb.fattt.pipeline;

import com.fatlamb.fattt.dao.PeopleDao;
import com.fatlamb.fattt.entity.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by 58 on 2016/12/30.
 */
public class testMybatis {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis_config.xml";
        try{
            InputStream is = Resources.getResourceAsStream(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = factory.openSession();
            PeopleDao peopleDao = session.getMapper(PeopleDao.class);
            People people = peopleDao.getPeople(1);
            System.out.print(people.getName());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
