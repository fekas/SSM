package com.zhongbin.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongbin.bean.Car;

/**
 * ���� @ModelAttributeע��
 * 	���ȫ�ֶθ��µ����⣺��α�ֻ֤�����б仯�����ݣ�
 * 
 * @ModelAttribute
 * 	>���ڷ����ϣ���ǰ��Ŀ�귽������
 * 			������ǰ������ݿ��е���Ϣ����װ���档
 * 	>���ڲ����ϣ�ȡ����@ModelAttribute��ע�ķ����б��������
 * 
 * @author XiaoBin
 * 2020��9��17������4:03:06
 */

@Controller
public class ModelAttributeTestController {

	@RequestMapping("/updateCar")
	public String updateCar(@ModelAttribute("car")Car car,Map<String,Object> map,String a){
		/*
		 * Car����ķ�װ���̣�1.����һ��Car�������Գ�ʼֵ����null
		 * 				  2.���е����Ը�ֵ���ö���
		 * 	���⣺����ʱû�е�����Ϊnull��ȫ�ֶθ��»��������
		 * 	������������½����󣬴����ݿ����ó�Ҫ���µ����ݲ���װ����Ȼ�����ȫ�ֶθ��¡�(�� @ModelAttributeע��)
		 */
		
		//�����map�����ModelAttributeOnMethod�е�model������ͬһ������
		System.out.println(a);
		System.out.println(car);
		Set<Entry<String,Object>> entrySet = map.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			System.out.println(entry);
		}
		return "success";
	}
	
	@ModelAttribute
	public void ModelAttributeOnMethod(Model model){
		//ģ���������ݿ��в鵽��car
		Car car = new Car("Ferrari","red",999999.9f);
		
		System.out.println("���ݿ�鵽Ҫ���µ�ͼ����Ϣ�ǣ�"+car);
		//�����ݿ��в鵽��car��������
		model.addAttribute("car", car);
		
		System.out.println("@ModelAttribute��ע�ķ���...");
	}
}
