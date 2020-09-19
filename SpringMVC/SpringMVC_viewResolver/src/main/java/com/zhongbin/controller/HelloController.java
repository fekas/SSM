package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	/**
	 * ��ͼ������������ת����WEB-INF/pages/success.jsp
	 */
	@RequestMapping("/hello")
	public String hello(){
		return "success";
	}
	
	/**
	 * ���ص�����·����jspҳ��?
	 * 	>�����·��
	 * 	>��forward:/			forwardǰ׺��ת����������ͼ������ƴ��
	 * @return
	 */
	@RequestMapping("/handle01")
	public String handle01(){
		return "../../hello";
	}
	@RequestMapping("/handle02")
	public String handle02(){
		return "forward:/hello.jsp";		//ת������ǰ��Ŀ�µ�hello.jsp(ע�����"/"��ʾ��Ŀ��·�������ӱ�ʾ���·���׳���)
	}
	@RequestMapping("/handle03")
	public String handle03(){
		return "forward:/handle02";			//ת����������
	}
	/**
	 * �ض�������ҳ��
	 * 
	 * redirect:�ض���·��
	 * 	ԭ��Servlet�ض���
	 * 	reponse.sendRedirect("/hello.jsp")
	 * @return
	 */
	@RequestMapping("/handle04")
	public String handle04(){
		return "redirect:/hello.jsp";
	}
	@RequestMapping("/handle05")
	public String handle05(){
		return "redirect:/handle04";		//����ض���
	}
	
	/**
	 * JstlView֧�ֿ��ٹ��ʻ�
	 * 
	 * JavaWeb�Ĺ��ʻ�
	 * 	>�õ�һ��locale����
	 * 	>ʹ��ResourceBundle�󶨹��ʻ���Դ�ļ�
	 * 	>ʹ��ResourceBund.getString("key");��ȡ���ʻ������ļ��е�ֵ
	 * 	>webҳ����ʻ���ʹ��fmt��ǩ
	 * 		<fmt:setLocal>
	 * 		<fmt:setBundle>
	 * 		<fmt:message>
	 * 
	 * 
	 * ʹ��JstlView
	 * 	>��Spring������ʻ���Դ
	 * 	>��ҳ��ʹ��<fmt:message>ȡֵ����
	 * 
	 * 
	 * �������ֵ��forward:ǰ׺��ͼ�������Ͳ��ᴴ��JstlView������ʹ�ÿ��ٹ��ʻ�����
	 * ԭ�����org.springframework.web.servlet.view.UrlBasedViewResolver.createView(String, Locale)����
	 * 		����ͼ����ǰ׺��ʱ��ᴴ��InternalResourceView���Բ���ʹ�ÿ��ٹ��ʻ�
	 * @return
	 */
	@RequestMapping("/handle06")
	public String handle06(){
		return "login";
	}
}
