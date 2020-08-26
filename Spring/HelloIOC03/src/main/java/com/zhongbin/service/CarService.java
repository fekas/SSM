package com.zhongbin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  javax.annotation.Resource;
import com.zhongbin.bean.Car;
import com.zhongbin.dao.CarDao;

@Service
public class CarService {

	//下面注解都可以进行自动装配
	@Autowired		//最强大，Spring自己的注解
	//@Resource		//j2ee的标准,扩展性更强，切换成另外一个容器框架还可以被认识。
	//@inject		//EJB，已过时
	private CarDao carDao;
	
	public void save(){
		System.out.println("CarService正在调用Dao为你保存Car");
		carDao.saveCar();
	}
	
	/*
	 * 方法参数上也可以标注解
	 * 容器启动的时候会运行这个方法并且自动注入参数列表里的属性
	 */
	@Autowired
	public void haha(CarDao carDao){
		System.out.println(Car.class);
	}
}
