package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/14
 * @Param
 * @return
 **/
public class PCUtil {

    //webDriver显性等待
    //直到传进来的xpath元素找到后driver才进行后面的操作
    //如果没有找到会返回异常
    public void wait(WebDriver driver,int time,String xpath){
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));

    }

}
