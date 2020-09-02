package com.zhongbin.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * ��ν��������֪ͨ����׼ȷ����
 * @author XiaoBin
 * 2020��9��1������3:50:58
 */

@Aspect
@Component
//@order �ı���������ȼ���
public class AOPLogUtils {
	
/**	����Spring�ĸ�����ʲôʱ������
	try{
		@Before						ǰ��֪ͨ
		method.invoke(obj,args);
		@AfterReturning				����֪ͨ
	}catch(e){
		@AfterThrowing				�쳣֪ͨ
	}finally{
		@After						����֪ͨ
	}
	
	@Around							����֪ͨ
	*
	*֪ͨ������ִ��˳��
	*	����ִ�У�@Before==>@After==>@AfterReturning
	*	�쳣ִ�У�@Before==>@After==>@AfterThrowing
	*
	*@Before
	*{
	*	try{
	*		����ǰ��
	*		Ŀ�귽��ִ��
	*		���Ʒ���
	*	}catch(e){
	*		���Ƴ����쳣
	*	}finally{
	*		���ƺ���
	*	}
	*}
	*@After
	*@AfterReturning/@AfterThrowing
	*
	*�µ�˳��
	*	����ǰ��==>@Before==>Ŀ�귽��ִ��==>���Ʒ���/�����쳣==>���ƺ���==>@After==>@AfterReturning/@AfterThrowing
	*/
	
	/**
	 * ִ��Ŀ�귽��֮ǰ,д�������ʽ
		execution(����Ȩ�޷� ����ֵ���� ����ǩ��)
		ͨ��� * �� ..
		֧��&& || ��
		��֪ͨ�������е�ʱ���õ�Ŀ�귽������ϸ��Ϣ
			��֪ͨ�����Ĳ����б���дJionPoint����
				JionPoint��װ��Ŀ�귽������ϸ��Ϣ
			�ڷ���֪ͨ�Ĳ����б�����Ӳ���Object result�����ܷ���ֵ����AfterReturning��returning���Ը���spring��ʲô���ܷ���ֵ
			�쳣֪ͨ���Ʒ���֪ͨ
			��������쳣�����ͺͽ����쳣�Ĳ��������Ͳ�һ�������������������ᱻ���ã�����֪ͨ����	���Խ��ܷ��ص����͵ķ�Χ������Ҫ��һЩ
	
			
		��ȡ�������ʽ��
		1.�������һ��void���͵Ŀշ���
		2.�����������@Pointcutע��,������ע�͵�valueֵ��@Pointcut��Ȼ���÷�������������������valueֵ
	*/
	@Pointcut("execution(public int com.zhongbin.impl.MyMathCalcultor.*(*, int))")
	public void myPointcut(){}
	@Before("myPointcut()")
	public static void logStart(JoinPoint joinPoint){
		//��ȡĿ�귽������ʱʹ�õĲ���
		Object[] args = joinPoint.getArgs();
		//��ȡ����ǩ��
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]������ʼִ�У��õĲ����б�"+Arrays.asList(args)+"��");
	}
	
	//Ŀ�귽������������ִ��
	@AfterReturning(value="myPointcut()",returning="result")
	public static void logReturn(JoinPoint joinPoint,Object result){
		//��ȡ����ǩ��
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ����ɣ��������ǣ�"+result);
	}
	
	//Ŀ�귽�������쳣��ʱ��ִ��
	@AfterThrowing(value="myPointcut()",throwing="e")
	public static void logException(JoinPoint joinPoint,Exception e){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ��ʱ�����쳣���쳣��Ϣ��"+e.getCause());
	}
	
	//Ŀ�귽��������ʱ��ִ��
	@After("myPointcut()")
	public static void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]����ִ�н�����");
	}
	
	/**
	 * ����֪ͨ@Around ��ǿ���֪ͨ�����Ƕ�̬����
	 * try{
	 * 		//ǰ��֪ͨ
	 * 		method.invoke(obj,args);
	 * 		//����֪ͨ
	 * }catch{
	 * 		//�쳣֪ͨ
	 * }finally{
	 * 		//����֪ͨ
	 * }
	 * 
	 * �ĺ�һ֪ͨ==>��֪ͨ
	 * @throws Throwable 
	 * 
	 */
	@Around("myPointcut()")
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
	
	/**
	 * �������ͬʱ����һ��Ŀ�귽��������˳��
	 */
}
