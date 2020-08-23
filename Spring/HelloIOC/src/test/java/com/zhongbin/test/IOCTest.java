package com.zhongbin.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhongbin.bean.Person;
import com.zhongbin.bean.User;
import com.zhongbin.bean.Car;

/**
 * 类路径的开始：
 * 	所有的源码包都会被合并放在类路径里
 * 		Java项目：/bin/
 * 		web项目：/WEB-INF/classes/
 * 
 * spring接管的项目标志了小S
 * @author HP
 *
 */
public class IOCTest {

	private static final String Car = null;
	/**
	 * 容器中的对象在容器创建完成时就已经创建完成。
	 * 用property标签时ioc容器利用setter方法为Java bean赋值，所以ioc把setter方法名的set去掉后首字母小写作为属性名并赋值。
	 * 		因此getter/setter方法命名必须符合规范。JavaBean的属性名由getter/setter方法决定。
	 */
	
	//ApplicationContext代表ioc容器
	//ClassPathXmlApplicationContext：当前应用的xml配置文件在ClassPath下(maven项目应放在src/main/java下或者新建一个源码文件)
	//根据配置文件得到ioc容器对象
	ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc3.xml");
	/*ioc配置在磁盘路径下
	ApplicationContext ioc = new 
			FileSystemXmlApplicationContext
			("C:\\Users\\HP\\Desktop\\Code\\SSM\\Spring\\HelloIOC\\src\\main\\java\\ioc.xml");*/

	@Test
	public void test01() {
				//获取ioc容器中的对象
		Person bean01 = (Person)ioc.getBean("Person01");
		Person bean02 = (Person)ioc.getBean("Person01");
		
		/*获取没有的组件：报异常。
		 * Person bean03 = (Person)ioc.getBean("Person02");
			org.springframework.beans.factory.NoSuchBeanDefinitionException:
			No bean named 'Person02' is defined*/
	
		//同一组件在容器中是单实例的
		System.out.println(bean01 == bean02);
	}
	@Test
	public void test02() {
		//通过id找
		Person bean01 = (Person)ioc.getBean("Person01");
		
		//通过类型来找
		//Person bean02 = ioc.getBean(Person.class);
		//若ioc中有多个Person类型的实例则报错
			//org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
			//No qualifying bean of type [com.zhongbin.bean.Person] is defined: 
			//expected single matching bean but found 2: Person01,Person02

		//通过id+类型（避免强转）
		Person bean03 = ioc.getBean("Person02", Person.class);
		//System.out.println(bean02);
		
		Object bean04 = ioc.getBean("Person06");
		System.out.println(bean04);
	}
	
	@Test
	public void test03() {
		User bean01 = (User)ioc.getBean("user04");
		Car car01 = ioc.getBean(Car.class);
		System.out.println(car01);
		System.out.println(bean01.getCar());
	}

	@Test
	public void test04() {
	
//		当user02为抽象类时获取实例会报错：
//		org.springframework.beans.factory.BeanIsAbstractException:
//			Error creating bean with name 'user02': Bean definition is abstract
		Object user02 = ioc.getBean("user02");
		
		Object user05 = ioc.getBean("user05");
		
		System.out.println(user02);
		System.out.println(user05);
	}
	
	@Test
	public void test05() {
		Object user01 = ioc.getBean("myFactoryBeanImpl");
		System.out.println(user01);
	}
}
