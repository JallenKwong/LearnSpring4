package sample.unitils.spring;


import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import com.smart.service.UserService;
import static org.testng.Assert.*;

public class UserServiceTest extends UnitilsTestNG {

	@SpringApplicationContext({"smart-service.xml", "smart-dao.xml"})
	private ApplicationContext applicationContext;
	
	@SpringBean("userService")
	private UserService userService;
	
	@Test
	public void testLoadSpringBean(){
		assertNotNull(applicationContext);
		
		//注意，unitils.properties有执行数据库脚本
		assertNotNull(userService.findUserByUserName("admin"));
	}
}
