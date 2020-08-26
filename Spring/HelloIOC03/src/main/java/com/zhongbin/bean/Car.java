package com.zhongbin.bean;

public class Car {
	private String brand;
	private String color;
	private Float price;

	public void myInit(){
		System.out.println("car的初始化方法...");
	}
	
	public void myDestory(){
		System.out.println("car的销毁方法...");
	}
	
	public Car() {
		super();
		System.out.println("Car实例被创建...");
		// TODO Auto-generated constructor stub
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", price=" + price + "]";
	}
	

}
