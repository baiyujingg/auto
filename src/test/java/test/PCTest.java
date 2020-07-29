package test;

import config.PCConfig;
import element.TestElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/*
 * @Author chendong
 * @Description //TODO
 * @Date  2020/7/13
 * @Param
 * @return
 **/
public class PCTest {

    WebDriver driver;

    //初始化webDriver
    //测试前的准备,指定驱动类型、路径,设置window全屏、隐式等待、登录
    @BeforeTest
    public void setDriver() {

        //设置浏览器位置
        ChromeOptions options = new ChromeOptions();
        options.setBinary(PCConfig.chromePath);
        //设置chrome驱动位置
        System.setProperty("webdriver.chrome.driver",PCConfig.chromeDriverPath);

        driver = new ChromeDriver();

        //全屏和隐式等待
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);

        //打开首页登录地址
        driver.get(TestElement.url);

        assert driver.getTitle().equals("百度一下，你就知道");
    }

    //测试后的操作,关闭浏览器
    @AfterTest
    public void closeDriver() {
        driver.close();
    }

    @Test
    public void search_1() {
        driver.findElement(By.id(TestElement.searchKw)).sendKeys("something");
        driver.findElement(By.id(TestElement.searchSu)).click();
        //利用url去断言，获取的url是否包含了关键字something
        //然而事实上，这种方法不可取，因为页面渲染是在url后进行的，所以拿到的有可能还是http://www.baidu.com这个url
        //所以这个case不一定会测试成功
        assert  driver.getCurrentUrl().contains("something");


    }

    @Test
    public void search_2() {

        //浏览器驱动找到“kw”元素的位置输入文本“something”, .sendKeys():往指定位置输入文本内容
        driver.findElement(By.id(TestElement.searchKw)).sendKeys("something");

        //驱动找到id元素“su”进行点击操作,   .click():往指定位置点击
        driver.findElement(By.id(TestElement.searchSu)).click();

        //获取当前窗口的句柄,句柄可以理解为窗口的ID,当你操作浏览器每新开一个浏览器窗口,浏览器就会为这个窗口生成一个ID,即句柄
        String windowHandle = driver.getWindowHandle();

        //这里的操作是点击第一个搜索出来的连接,点击完之后,浏览器会为我们新开一个窗口
        driver.findElement(By.xpath(TestElement.firstLink)).click();

        //由于点击完之后,页面是新开的一个网页窗口,而selenium不能直接从当前的窗口操作新开的窗口,所以下面需要进行切换窗口

        //获取所有窗口的句柄(ID),获取到的句柄用set集合接收
        Set<String> windowHandles = driver.getWindowHandles();

        //前面已经获取了第一个窗口的句柄,此时只有两个窗口,那么只要把set集合中第一个句柄移除掉,剩下的就是第二个窗口的句柄
        windowHandles.remove(windowHandle);

        //此时集合中剩下的只有第二个窗口的句柄,但是还要把它取出来,跟第一个句柄一样用String去接收它
        String nextWindow = windowHandles.iterator().next();

        //切换句柄,即切换窗口
        driver.switchTo().window(nextWindow);

        //断言,验证指定位置的元素文本是不是"牛津词典"
        assert
                driver.findElement(By.xpath(TestElement.headerText))
                        .getText()
                        .equals("牛津词典");

        //关闭浏览器,注意我们之前切换了窗口,所以此时关闭的是第二个窗口
        driver.close();

        //需要把窗口重新切回到第一个,否则  @AfterTest  测试后操作不能把第一个窗口关闭
        driver.switchTo().window(windowHandle);
    }

}
