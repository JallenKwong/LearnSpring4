package com.smart.beanfactory;

import java.io.IOException;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.smart.Car;

public class BeanFactoryTest {
	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource res = resolver.getResource("classpath:com/smart/beans1.xml");
		
		System.out.println(res.getURL());
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		
		reader.loadBeanDefinitions(res);
		
		System.out.println("init BeanFactory.");
		
		Car car = factory.getBean("car1", Car.class);
		
		System.out.println("car bean is ready for use!");
		
		car.introduce();
		
		
		
	}
}
