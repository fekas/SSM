package com.zhongbin.View;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

/**
 * �Զ�����ͼ
 * @author XiaoBin
 * 2020��9��19������8:44:19
 */
public class MyView implements View{

	/**
	 * ���ص����ݵ���������
	 */
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("֮ǰ���������"+model);
		//�����������ͣ��ַ������������forceEncoding="true"ֻ��������Ӧ�����ʽ
		response.setContentType("text/html");
		response.getWriter().write("����<h1>ͼƬ������</h1>");
	}

}
