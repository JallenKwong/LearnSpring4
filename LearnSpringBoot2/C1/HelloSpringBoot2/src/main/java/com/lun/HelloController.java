package com.lun;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController相当于SpringMVC中的 @Controller + @ResponseBody
 * 
 * @author 白居布衣
 *
 */
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring Boot, I'm learning you.";
	}

}
