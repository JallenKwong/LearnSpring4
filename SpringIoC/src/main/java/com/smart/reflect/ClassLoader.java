package com.smart.reflect;


public class ClassLoader {
	public static void main(String[] args) {
		java.lang.ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
	}
}
