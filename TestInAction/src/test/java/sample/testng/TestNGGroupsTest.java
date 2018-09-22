
package sample.testng;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * 有两种方法分组测试
 * 
 * 1. 是在pom.xml的测试插添加
 * 
 *<suiteXmlFiles>
 *	<suiteXmlFile>src/test/java/sample/testng/testng.xml</suiteXmlFile>
 *</suiteXmlFiles>
 * 
 * or
 * 
 * 2.Test右键Run as -> Run Configurations 选择填写testng.xml的决定地址
 * 
 * @author 白居布衣
 *
 */
@Test(groups = {"class-group"})
public class TestNGGroupsTest {

    @Test(groups = {"group1", "group2"})
    public void testMethod1() {
    }

    @Test(groups = {"group1", "group2"})
    public void testMethod2() {
    }

    @Test(groups = {"group2"})
    public void testMethod3() {
    }

}
