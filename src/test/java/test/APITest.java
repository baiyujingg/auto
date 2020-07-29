package test;

import api.Api;
import com.alibaba.fastjson.JSONObject;
import entity.Greeting;
import org.testng.annotations.Test;
import response.Response;
import util.APIUtil;



/*
 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/16
 * @Param
 * @return
 **/

public class APITest {

    Response response ;
    APIUtil util = new APIUtil();
    Greeting greeting = new Greeting();


    @Test
    public void testPost(){

        //doPost
        Response response = util.doPost(Api.doPostApi, greeting);

        //拿到Response.class中的map中的data字段的值
        Object data = response.getMap().get("data");

        //data是一个Json
        /*{
            "args": {},
            "data": "{\"greeting\":\"hello\"}",
                "files": {},
            "form": {},
            "headers": {
            "Accept-Encoding": "gzip,deflate",
                    "Content-Length": "20",
                    "Content-Type": "application/json;charset=utf8",
                    "Host": "www.httpbin.org",
                    "User-Agent": "Apache-HttpClient/4.5.9 (Java/1.8.0_151)",
                    "X-Amzn-Trace-Id": "Root=1-5f190175-334b121ad75e89d6d59be555"
        },
            "json": {
            "greeting": "hello"
        },
            "origin": "222.247.217.221",
                "url": "http://www.httpbin.org/post"
        }*/

        //然后在拿到Json中data字段的值,拿出来这个是为了断言,判断接口请求是不是成功的
        //这里可以根据自己接口返回的数据自行更改
        String jsonData = JSONObject.parseObject(data.toString()).get("data").toString();

        //断言
        assert jsonData.equals("{\"greeting\":\"hello\"}") && response.getState()==200;
    }

    @Test
    public void testGet_1(){
        //doGet,no parameters

        Response response = util.doGet(Api.doGetNoParameters);

        //拿到Response.class中的map中的data字段的值
        Object data = response.getMap().get("data");

        //然后在拿到Json中origin字段的值,拿出来这个是为了断言,判断接口请求是不是成功的
        //这里可以根据自己接口返回的数据自行更改
        String jsonData = JSONObject.parseObject(data.toString()).get("origin").toString();

        //断言
        assert jsonData.equals("222.247.217.221") && response.getState()==200;
    }

    @Test
    public void testGet_2(){
        //doGet,has parameters
        Response response = util.doGet(Api.doGetNoParameters,greeting);

        //拿到Response.class中的map中的data字段的值
        Object data = response.getMap().get("data");

        //然后在拿到Json中origin字段的值,拿出来这个是为了断言,判断接口请求是不是成功的
        //这里可以根据自己接口返回的数据自行更改
        String jsonData = JSONObject.parseObject(data.toString()).get("url").toString();

        //断言
        assert jsonData.equals("http://www.httpbin.org/get?greeting=hello") && response.getState()==200;
    }
}
