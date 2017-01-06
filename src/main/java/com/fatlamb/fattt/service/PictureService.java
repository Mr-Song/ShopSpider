package com.fatlamb.fattt.service;

import com.fatlamb.fattt.constant.Constants;
import com.fatlamb.fattt.util.HttpClientUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by 58 on 2017/1/6.
 */
@Component
public class PictureService {
    public String getPictureByUrl(String url , String source){
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        CloseableHttpResponse response = null;
        String fileName = url.substring(url.lastIndexOf("/"));
        String path = Constants.Path.PIC_PATH + source + File.separator + fileName;
        try{
            HttpGet get = new HttpGet(url);
            response = httpClient.execute(get);
            if(response.getStatusLine().getStatusCode() != 200){
                return null ;
            }

        }catch (Exception e){

        }finally {

        }
        return path ;
    }
}
