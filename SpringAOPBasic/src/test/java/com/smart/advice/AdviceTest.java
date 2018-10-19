package com.smart.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class AdviceTest {

	/**
	 * 前置增强
	 */
	@Test
	public void advice() {
		String configPath = "com/smart/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		waiter.greetTo("John");
	}
	
	/**
	 * 前置增强和后置增强
	 */
	@Test
	public void advice2() {
		String configPath = "com/smart/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter2");
		waiter.greetTo("John");
	}
	
	/**
	 * 环绕增强
	 */
	@Test
	public void advice3() {
		String configPath = "com/smart/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
		Waiter waiter = (Waiter)ctx.getBean("waiter3");
		waiter.greetTo("John");
	}
}
