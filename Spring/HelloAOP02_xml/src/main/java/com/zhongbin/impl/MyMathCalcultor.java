package com.zhongbin.impl;

import com.zhongbin.inter.Calculator;

public class MyMathCalcultor implements Calculator{

	public int add(int i, int j) {
		//��������޸�ά���鷳��ϵͳ��϶ȸ�
		//1.дһ����������Ǽ�¼��־
//		LogUtils.logStart(i,j);
		int result = i + j;
		//2.�ڷ����ڲ�д��־
//		System.out.println("[add]����������ɣ����صĽ���ǣ�"+result);
		return result;
	}

	public int sub(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i - j;
//		System.out.println("[sub]����������ɣ����صĽ���ǣ�"+result);
		return result;
	}

	public int mul(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i * j;
//		System.out.println("[mul]����������ɣ����صĽ���ǣ�"+result);
		return result;
	}

	public int div(int i, int j) {
//		LogUtils.logStart(i,j);
		int result = i / j;
//		System.out.println("[div]����������ɣ����صĽ���ǣ�"+result);
		return result;
	}
	

}
