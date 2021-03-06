package com.zhongbin.impl;

import org.springframework.stereotype.Service;

import com.zhongbin.inter.Calculator;
import com.zhongbin.utils.LogUtils;

@Service
public class MyMathCalcultor implements Calculator{

	@Override
	public int add(int i, int j) {
		//都会造成修改维护麻烦，系统耦合度高
		//1.写一个类帮我我们记录日志
//		LogUtils.logStart(i,j);
		int result = i + j;
		//2.在方法内部写日志
//		System.out.println("[add]方法运行完成，返回的结果是："+result);
		return result;
	}

	@Override
	public int sub(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i - j;
//		System.out.println("[sub]方法运行完成，返回的结果是："+result);
		return result;
	}

	@Override
	public int mul(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i * j;
//		System.out.println("[mul]方法运行完成，返回的结果是："+result);
		return result;
	}

	@Override
	public int div(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i / j;
//		System.out.println("[div]方法运行完成，返回的结果是："+result);
		return result;
	}
	

}
