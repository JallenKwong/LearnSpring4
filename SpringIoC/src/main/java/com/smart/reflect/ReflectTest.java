package com.smart.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.ClassLoader;

public class ReflectTest {
	public static Car initByDefaultConst() throws Throwable{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.smart.reflect.Car");
		
		Constructor cons = clazz.getConstructor((Class[])null);
		
		Car car = (Car)cons.newInstance();	
		
		Method setBrand = clazz.getMethod("setBrand", String.class);
		setBrand.invoke(car, "奔驰");
		
		Method setColor = clazz.getMethod("setColor", String.class);
		setBrand.invoke(car, "银色");
		
		Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
		setMaxSpeed.invoke(car, 200);
		
		return car;
	}
	
	public static void main(String[] args) throws Throwable {
		Car car = initByDefaultConst();
		car.introduce();
	}
}
