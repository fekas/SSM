package com.zhongbin.inter;

//接口一般不加在容器中，加进去spring也不会为它创建组件
public interface Calculator {
	
	public int add(int i,int j);
	public int sub(int i,int j);
	public int mul(int i,int j);
	public int div(int i,int j);
}
