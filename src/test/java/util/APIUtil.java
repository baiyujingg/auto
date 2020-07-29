package util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import response.Response;

import java.io.IOException;


/*
 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/15
 * @Param
 * @return
 **/
public class APIUtil {

    /**提供了三格调取接口的方法,都会把返回的状态和参数封装到Response类中并返回初来.**/

    //doGet
    public Response doGet(String url){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        Response responseContent = new Response();

        HttpGet get = new HttpGet(url);

        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(get);
            HttpEntity responseEntity = response.getEntity();

            String stateLine = response.getStatusLine().toString();

            //根据实际返回码情况可自己增加
            if (stateLine.contains("200")){
                responseContent.setState(200);
            }
            if (stateLine.contains("500")){
                responseContent.setState(500);
            }
            if (stateLine.contains("400")){
                responseContent.setState(400);
            }
            //根据实际返回码情况可自己增加

            responseContent.map.put("data",EntityUtils.toString(responseEntity));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return responseContent;

    }

    //doGet,this doGet request has parameters.
    public Response doGet(String url , Object t){

        DataUtil dataUtil = new DataUtil();
        Response responseContent = new Response();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String params = dataUtil.dataProcess(t);

        HttpGet get = new HttpGet(url+"?"+params);

        CloseableHttpResponse response = null;
        try{
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//设置连接超时时间
                    .setConnectionRequestTimeout(5000)//设置请求超时时间
                    .setSocketTimeout(5000)//设置读写超时时间
                    .setRedirectsEnabled(true)//设置是否运行重定向
                    .build();
            get.setConfig(requestConfig);
            response = httpClient.execute(get);
            HttpEntity responseEntity = response.getEntity();

            String stateLine = response.getStatusLine().toString();

            //根据实际返回码情况可自己增加
            if (stateLine.contains("200")){
                responseContent.setState(200);
            }
            if (stateLine.contains("500")){
                responseContent.setState(500);
            }
            if (stateLine.contains("400")){
                responseContent.setState(400);
            }

            //根据实际返回码情况可自己增加
            responseContent.map.put("data",EntityUtils.toString(responseEntity));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } return responseContent;
    }

    //doPost
    public Response doPost(String url , Object t){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        Response responseContent = new Response();

        HttpPost httpPost = new HttpPost(url);

        String json = JSON.toJSONString(t);

        StringEntity entity = new StringEntity(json, "UTF-8");

        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            String stateLine = response.getStatusLine().toString();

            //根据实际返回码情况可自己增加
            if (stateLine.contains("200")){
                responseContent.setState(200);
            }
            if (stateLine.contains("500")){
                responseContent.setState(500);
            }
            if (stateLine.contains("400")){
                responseContent.setState(400);
            }
            //根据实际返回码情况可自己增加

            responseContent.map.put("data",EntityUtils.toString(responseEntity));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return responseContent;
    }

}
