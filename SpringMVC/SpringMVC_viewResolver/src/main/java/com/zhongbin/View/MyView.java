package com.zhongbin.View;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

/**
 * 自定义视图
 * @author XiaoBin
 * 2020年9月19日下午8:44:19
 */
public class MyView implements View{

	/**
	 * 返回的数据的内容类型
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
		System.out.println("之前保存的数据"+model);
		//设置内容类型，字符编码过滤器的forceEncoding="true"只设置了响应编码格式
		response.setContentType("text/html");
		response.getWriter().write("哈哈<h1>图片加载中</h1>");
	}

}
