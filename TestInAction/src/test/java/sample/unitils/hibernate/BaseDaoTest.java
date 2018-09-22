package sample.unitils.hibernate;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.orm.hibernate.annotation.HibernateSessionFactory;

@HibernateSessionFactory("hibernate.cfg.xml")
public class BaseDaoTest extends UnitilsTestNG {

	@HibernateSessionFactory
	protected SessionFactory sessionFactory;
	
	@Test
	public void testSessionFactory() {
		Assert.assertNotNull(sessionFactory);
	}
	
	
}
