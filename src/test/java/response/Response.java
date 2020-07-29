package response;


import lombok.Data;

import java.util.HashMap;


/*
 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/17
 * @Param
 * @return
 **/


//用来接收接口返回的状态和参数
@Data
public class Response {
    public HashMap<String , Object> map = new HashMap<String, Object>();
    public int state;
}
