package com.zhongbin.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AOPLogUtils {
	
	public static void logStart(JoinPoint joinPoint){
		//获取目标方法运行时使用的参数
		Object[] args = joinPoint.getArgs();
		//获取方法签名
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法开始执行，用的参数列表【"+Arrays.asList(args)+"】");
	}
	
	public static void logReturn(JoinPoint joinPoint,Object result){
		//获取方法签名
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行完成，计算结果是："+result);
	}
	
	//目标方法出现异常的时候执行
	public static void logException(JoinPoint joinPoint,Exception e){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行时出现异常，异常信息是"+e.getCause());
	}
	
	//目标方法结束的时候执行
	public static void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行结束了");
	}
	
	public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		//利用反射执行目标方法,就是method.invoke(obj,args)
		Object proceed = null;
		try {
			System.out.println(name+"的环绕前置通知");
			proceed = joinPoint.proceed(args);
			System.out.println(name+"的环绕返回通知，结果："+proceed);
		} catch (Exception e) {
			System.out.println(name+"的环绕异常通知"+e);
			//抛出异常让外界能看到这个异常
			throw new RuntimeException(e);
		}finally{
			System.out.println(name+"的环绕后置通知");
		}
		
		//反射后的返回值也要返回出去
		return proceed;
	}
}
