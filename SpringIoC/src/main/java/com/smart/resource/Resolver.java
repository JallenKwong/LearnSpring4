package com.smart.resource;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class Resolver {

	public static void main(String[] args) throws IOException {
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		Resource resources[] = resolver.getResources("classpath:com/smart/**/*.xml");
		
		for(Resource r : resources) {
			System.out.println(r.getDescription());
		}
		
	}

}
/*
file [C:\eclipse-workspace\SpringIoC\target\classes\com\smart\beanfactory\beans.xml]
file [C:\eclipse-workspace\SpringIoC\target\classes\com\smart\beans1.xml]
file [C:\eclipse-workspace\SpringIoC\target\classes\com\smart\beans2.xml]
file [C:\eclipse-workspace\SpringIoC\target\classes\com\smart\context\beans.xml]
 */

