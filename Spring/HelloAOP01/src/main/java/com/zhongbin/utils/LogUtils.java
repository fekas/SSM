package com.zhongbin.utils;

import java.lang.reflect.Method;

public class LogUtils {

	public static void logStart(Method method,Object...args){
		System.out.println("[xxx]������ʼִ�У��õĲ����б�xxx��");
	}
	
	public static void logReturn(Method method,Object result){
		System.out.println("[xxx]����ִ����ɣ��������ǣ�");
	}
	
	public static void logException(Method method,Exception e){
		System.out.println("[xxx]����ִ��ʱ�����쳣���쳣��Ϣ��");
	}
	
	public static void logEnd(Method method){
		System.out.println("[xxx]����ִ�н�����");
	}
}
