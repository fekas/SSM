package com.zhongbin.ViewResolver;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.zhongbin.View.MyView;

public class MyPictureViewResolver implements ViewResolver,Ordered{

	private Integer order = 0;
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		//������ͼ��������ͼ����
		if(viewName.startsWith("dabin:")){
			return new MyView();
		}else{
			//������ܴ�������null����
			return null;
		}
	}
	
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return order;
	}
	//�ı���ͼ�����������ȼ�
	public void setOrder(Integer order){
		this.order=order;
	}

}
