package com.zhongbin.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User {
	private String name = "XXX";
	private String sex;
	private String email;
	private Float blance;
	
	public Float getBlance() {
		return blance;
	}



	public void setBlance(Float blance) {
		this.blance = blance;
	}

	private Car car;
	
	private List<Car> cars;
	
	private Map<String,Object> maps;
	
	private Properties properties;
	
	

	public User() {
		super();
		System.out.println("user实例被创建...");
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Car car) {
		super();
		this.car = car;
		System.out.println("可以为car赋值的有参构造器...");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", sex=" + sex + ", email=" + email + ", blance=" + blance + ", car=" + car
				+ ", cars=" + cars + ", maps=" + maps + ", properties=" + properties + "]";
	}

}
