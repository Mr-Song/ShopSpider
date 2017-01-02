package com.fatlamb.fattt.dao;

import com.fatlamb.fattt.entity.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by hasee on 2017/1/1.
 */
public class DBHelper {
    private static final String resource = "mybatis/mybatis_config.xml";
    private static SqlSessionFactory factory = null ;

    private static SqlSessionFactory getSessionFactory(){
        if(factory == null){
            InputStream is = null;
            try {
                is = Resources.getResourceAsStream(resource);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            factory = new SqlSessionFactoryBuilder().build(is);

        }
        return factory;
    }

    public static <T> T getMapper(Class<T> clazz){
        SqlSession session = getSessionFactory().openSession(true);
        T dao = session.getMapper(clazz);
        return dao ;

    }

}
