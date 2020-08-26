package com.zhongbin.dao;

import org.springframework.stereotype.Repository;

import com.zhongbin.bean.Car;

@Repository
public class CarDao extends BaseDao<Car>{

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("CarDao在保存车辆");
	}

}
