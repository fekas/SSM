package com.zhongbin.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * 组件通过注解加入容器后，id默认为类名首字母小写。可以用@Repository("carDao01")修改id名
 * @author XiaoBin
 * 2020年8月26日上午8:59:05
 */
@Repository
//默认为单实例，可通过@Scope注解修改
//@Scope(value="prototy")
public class CarDao {

	public void saveCar(){
		System.out.println("Car已被保存");
	}
}
