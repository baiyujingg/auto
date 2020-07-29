import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

 /*
  * @Author chendong
  * @Description //TODO
  * @Date  2020/7/17
  * @Param
  * @return
  **/


public class Run {
   public static void main(String[] args) {
       TestNG testNG = new TestNG();
       List<String> suites = Lists.newArrayList();

       //指定运行的xml文件
       suites.add("testng.xml");
       testNG.setTestSuites(suites);
       testNG.run();
   }
}
