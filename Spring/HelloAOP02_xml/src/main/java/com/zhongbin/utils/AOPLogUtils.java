package com.zhongbin.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AOPLogUtils {
	
	public static void logStart(JoinPoint joinPoint){
		//��ȡĿ�귽������ʱʹ�õĲ���
		Object[] args = joinPoint.getArgs();
		//��ȡ����ǩ��
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]������ʼִ�У��õĲ����б�"+Arrays.asList(args)+"��");
	}
	
	public static void logReturn(JoinPoint joinPoint,Object result){
		//��ȡ����ǩ��
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ����ɣ��������ǣ�"+result);
	}
	
	//Ŀ�귽�������쳣��ʱ��ִ��
	public static void logException(JoinPoint joinPoint,Exception e){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ��ʱ�����쳣���쳣��Ϣ��"+e.getCause());
	}
	
	//Ŀ�귽��������ʱ��ִ��
	public static void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ�н�����");
	}
	
	public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		//���÷���ִ��Ŀ�귽��,����method.invoke(obj,args)
		Object proceed = null;
		try {
			System.out.println(name+"�Ļ���ǰ��֪ͨ");
			proceed = joinPoint.proceed(args);
			System.out.println(name+"�Ļ��Ʒ���֪ͨ�������"+proceed);
		} catch (Exception e) {
			System.out.println(name+"�Ļ����쳣֪ͨ"+e);
			//�׳��쳣������ܿ�������쳣
			throw new RuntimeException(e);
		}finally{
			System.out.println(name+"�Ļ��ƺ���֪ͨ");
		}
		
		//�����ķ���ֵҲҪ���س�ȥ
		return proceed;
	}
}
