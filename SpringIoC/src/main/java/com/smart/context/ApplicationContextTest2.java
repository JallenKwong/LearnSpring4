package com.smart.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.Car;

public class ApplicationContextTest2 {
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("com/smart/beanfactory/beans2.xml");
		
		Car car = ctx.getBean("car", Car.class);
		car.introduce();
	}
}
