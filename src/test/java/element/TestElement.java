package element;


import lombok.Data;

/*

 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/23
 * @Param
 * @return
 **/

@Data
public class TestElement {
    public static String url = "http://www.baidu.com";


    public static String searchKw = "kw";
    public static String searchSu = "su";

    public static String firstLink = "//*[@id=\"1\"]/h3/a";

    public static String headerText = "//*[@id=\"left-result-container\"]/section[1]/header";
}
