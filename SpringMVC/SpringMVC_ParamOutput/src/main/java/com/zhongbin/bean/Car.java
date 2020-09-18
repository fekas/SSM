package com.zhongbin.bean;

public class Car {
	private String brand;
	private String color;
	private Float price;

	public Car() {
		super();
	}
	
	public Car(String brand, String color, Float price) {
		super();
		this.brand = brand;
		this.color = color;
		this.price = price;
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
