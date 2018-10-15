package com.smart.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PrivateCarReflect {

	public static void main(String[] args) throws Exception {
		java.lang.ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.smart.reflect.PrivateCar");
		
		PrivateCar car = (PrivateCar)clazz.newInstance();
		Field colorFld = clazz.getDeclaredField("color");
		colorFld.setAccessible(true);
		colorFld.set(car, "black");
		
		Method driveMtd = clazz.getDeclaredMethod("drive", null);
		
		driveMtd.setAccessible(true);
		driveMtd.invoke(car, null);
		
	}

}
