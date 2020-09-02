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
 * 如何将切面类的通知方法准确切入
 * @author XiaoBin
 * 2020年9月1日下午3:50:58
 */

@Aspect
@Component
//@order 改变切面的优先级。
public class AOPLogUtils {
	
/**	告诉Spring哪个方法什么时候运行
	try{
		@Before						前置通知
		method.invoke(obj,args);
		@AfterReturning				返回通知
	}catch(e){
		@AfterThrowing				异常通知
	}finally{
		@After						后置通知
	}
	
	@Around							环绕通知
	*
	*通知方法的执行顺序
	*	正常执行：@Before==>@After==>@AfterReturning
	*	异常执行：@Before==>@After==>@AfterThrowing
	*
	*@Before
	*{
	*	try{
	*		环绕前置
	*		目标方法执行
	*		环绕返回
	*	}catch(e){
	*		环绕出现异常
	*	}finally{
	*		环绕后置
	*	}
	*}
	*@After
	*@AfterReturning/@AfterThrowing
	*
	*新的顺序：
	*	环绕前置==>@Before==>目标方法执行==>环绕返回/出现异常==>环绕后置==>@After==>@AfterReturning/@AfterThrowing
	*/
	
	/**
	 * 执行目标方法之前,写切入点表达式
		execution(访问权限符 返回值类型 方法签名)
		通配符 * 和 ..
		支持&& || ！
		在通知方法运行的时候拿到目标方法的详细信息
			在通知方法的参数列表上写JionPoint参数
				JionPoint封装了目标方法的详细信息
			在返回通知的参数列表上添加参数Object result来接受返回值并用AfterReturning的returning属性告诉spring用什么接受返回值
			异常通知类似返回通知
			如果返回异常的类型和接受异常的参数的类型不一样，则该这个方法将不会被调用，返回通知类似	所以接受返回的类型的范围尽量需要大一些
	
			
		抽取切入点表达式：
		1.随便申明一个void类型的空方法
		2.给方法上添加@Pointcut注释,把其他注释的value值给@Pointcut，然后用方法名代替其他方法的value值
	*/
	@Pointcut("execution(public int com.zhongbin.impl.MyMathCalcultor.*(*, int))")
	public void myPointcut(){}
	@Before("myPointcut()")
	public static void logStart(JoinPoint joinPoint){
		//获取目标方法运行时使用的参数
		Object[] args = joinPoint.getArgs();
		//获取方法签名
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法开始执行，用的参数列表【"+Arrays.asList(args)+"】");
	}
	
	//目标方法正常结束后执行
	@AfterReturning(value="myPointcut()",returning="result")
	public static void logReturn(JoinPoint joinPoint,Object result){
		//获取方法签名
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行完成，计算结果是："+result);
	}
	
	//目标方法出现异常的时候执行
	@AfterThrowing(value="myPointcut()",throwing="e")
	public static void logException(JoinPoint joinPoint,Exception e){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行时出现异常，异常信息是"+e.getCause());
	}
	
	//目标方法结束的时候执行
	@After("myPointcut()")
	public static void logEnd(JoinPoint joinPoint){
		String name = joinPoint.getSignature().getName();
		System.out.println("["+name+"]方法执行结束了");
	}
	
	/**
	 * 环绕通知@Around 最强大的通知，就是动态代理
	 * try{
	 * 		//前置通知
	 * 		method.invoke(obj,args);
	 * 		//返回通知
	 * }catch{
	 * 		//异常通知
	 * }finally{
	 * 		//后置通知
	 * }
	 * 
	 * 四合一通知==>绕通知
	 * @throws Throwable 
	 * 
	 */
	@Around("myPointcut()")
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
	
	/**
	 * 多个切面同时切向一个目标方法的运行顺序
	 */
}
