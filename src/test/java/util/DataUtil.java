package util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.PropertyUtilsBean;
import response.Response;

import java.beans.PropertyDescriptor;
import java.util.HashMap;

public class DataUtil {

    //数据处理,将实体类中的参数遍历出来处理成get请求需要的形式
    //examples: userId=1&userName="张三"&sex=1
    public String dataProcess(Object t){

        StringBuilder params = new StringBuilder();
        try{
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(t);

            //遍历实体类t中的属性和参数,把属性和参数拼接起来就形成了get请求的基本形式
            //examples: userId=1&userName="1"&
            for (PropertyDescriptor descriptor : descriptors) {
                String name = descriptor.getName();
                if (!name.equals("class")) {
                    String param = name + "=" + propertyUtilsBean.getNestedProperty(t, name) + "&";
                    params.append(param);
                }
            }

            //把参数最后一个字符串去点(&)
            params = new StringBuilder(params.substring(0, params.length() - 1));
        }catch (Exception ignored){
        }return params.toString();
    }

    public JSONObject dataProcess(Response responseContent){
        HashMap<String, Object> map = responseContent.getMap();
        String data = map.get("data").toString();
        return JSONObject.parseObject(data);
    }

    //生成随机字符串的方法
    //需要传一个生成几位数的len变量,一个定义好的数组;

    public String randomStr(int len , String[] nums){
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < len; i++){
            int number = (int) (Math.random()*nums.length);
            String num = Integer.toString(number);
            str.append(num);
        }
        return str.toString();
    }
}
