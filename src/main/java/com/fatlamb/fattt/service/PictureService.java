package com.fatlamb.fattt.service;

import com.fatlamb.fattt.constant.Constants;
import com.fatlamb.fattt.util.FileUtil;
import com.fatlamb.fattt.util.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by 58 on 2017/1/6.
 */
public class PictureService {
    public static void main(String[] args){
        System.out.println(new PictureService().getPicturePath());
    }

    private static final PictureService instance = new PictureService();

    private PictureService(){}

    public static PictureService getInstance(){
        return instance;
    }

    public String getPicturePath(){
        String path = System.getProperty("web.root");
        path = path == null ? "/" : path ;
        path = path + "resources/static/pic/";
        System.out.println("get picture path:" + path);
        return path;

    }

    public String getPictureByUrl(String url , String source){
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        CloseableHttpResponse response = null;
        String fileName = url.substring(url.lastIndexOf("/"));
        String path = getPicturePath() + source + File.separator ;
        String fullPath = path + fileName;
        try{
            HttpGet get = new HttpGet(url);
            response = httpClient.execute(get);
            if(response.getStatusLine().getStatusCode() != 200){
                return null ;
            }
            byte[] img = EntityUtils.toByteArray(response.getEntity());
            FileUtil.createDirsIfNotExist(path);
            FileUtil.wirte(fullPath , img);
            return fileName;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(response != null){
                    response.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return path ;
    }
}
