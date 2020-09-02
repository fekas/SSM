package com.zhongbin.utils;

import java.lang.reflect.Method;

public class LogUtils {

	public static void logStart(Method method,Object...args){
		System.out.println("[xxx]方法开始执行，用的参数列表【xxx】");
	}
	
	public static void logReturn(Method method,Object result){
		System.out.println("[xxx]方法执行完成，计算结果是：");
	}
	
	public static void logException(Method method,Exception e){
		System.out.println("[xxx]方法执行时出现异常，异常信息是");
	}
	
	public static void logEnd(Method method){
		System.out.println("[xxx]方法执行结束了");
	}
}
