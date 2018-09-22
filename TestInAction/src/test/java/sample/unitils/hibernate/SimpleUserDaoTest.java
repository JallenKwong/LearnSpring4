package sample.unitils.hibernate;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;

import com.smart.dao.UserDao;
import com.smart.dao.hibernate.WithoutSpringUserDaoImpl;

/**
 * 
 * 	
 *	书本源码的unitils-orm:3.4.2 不支持 hibernate4,支持hibernate3
 *	
 *  较新版本3.4.6 依然未解决不兼容问题
 *
 *	解决之道：将hibernate版本从4换成3
 * 
 * 
 * @author 白居布衣
 *
 */
public class SimpleUserDaoTest extends BaseDaoTest{
	
	private UserDao userDao;
	
	@BeforeClass
	public void init() {
		userDao = new WithoutSpringUserDaoImpl();
		((WithoutSpringUserDaoImpl)userDao).setSessionFactory(sessionFactory);
	}
	
	public void testUserDao() {
		assertNotNull(userDao);
		assertNotNull(userDao.findUserByUserName("admin"));
		assertEquals("admin", userDao.findUserByUserName("admin").getUserName());
	}
	
	
}
