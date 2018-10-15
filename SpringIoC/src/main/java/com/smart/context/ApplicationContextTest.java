package com.smart.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.Car;

public class ApplicationContextTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext(new String[] {"com/smart/beans1.xml","com/smart/beans2.xml"});
		
		ctx.getBean("car1", Car.class).introduce();
		ctx.getBean("car2", Car.class).introduce();
		
	}

}
