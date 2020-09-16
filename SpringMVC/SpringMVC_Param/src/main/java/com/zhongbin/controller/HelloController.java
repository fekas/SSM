package com.zhongbin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@RequestMapping("hello")
	private String handle01() {
		System.out.println("handle01����hello����");
		return "success";
	}
	
	/*
	 * ��ȡ���������
	 * Ĭ�Ϸ�ʽ��ȡ������
	 * 	ֱ���ڷ��������б�������������������ͬ�ı�������������������ܲ�����ֵ��û����Ϊnull
	 */
	@RequestMapping("hello01")
	private String handle01(String username) {
		System.out.println("username��ֵ�ǣ�"+username);
		return "success";
	}
	
	
	/**
	 * �� @RequestParam ע���ȡ�������
	 * String value() default "";
	 * boolean required() default true;		Ĭ�ϱ������ָ�����ԣ����򱨴����޸�Ϊfalse��Ϊnull
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;	���ò���Ĭ��ֵ
	 * 
	 * ��@PathVariable������PathVariable��ȡ·���ϵ�ֵ
	 */
	@RequestMapping("hello02")
	private String handle02(@RequestParam(value="username", defaultValue="��û��",required=false)String user) {
		System.out.println("user��ֵ�ǣ�"+user);
		return "success";
	}
	
	/*
	 * @RequestHeader:��ȡ����ͷ��ĳ��key��ֵ,
	 * String value() default "";
	 * boolean required() default true;	Ĭ���������ͷ��û����������򱨴�
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;
	 * 		ȡ��request.getHeader("User-Agent");
	 */
	@RequestMapping("hello03")
	private String handle03(@RequestHeader("User-Agent")String userAgent) {
		System.out.println("User-Agent�ǣ�"+userAgent);
		return "success";
	}
	
	/*@CookieValue ��ȡĳ��cookie��ֵ
	 * String value() default "";
	 * boolean required() default true;
	 * String defaultValue() default ValueConstants.DEFAULT_NONE;
	 * 	ȡ��Cookie[] cookies = request.getCookies();
	 */
	@RequestMapping("hello04")
	private String handle04(@CookieValue("JSESSIONID")String jId) {
		System.out.println("Cookies�е�jId��"+jId);
		return "success";
	}
	
	/**
	 * SpringMVC����ֱ���ڲ�����дԭ��API��
	 * eg:HttpServletRequest HttpSession��HttpResponse��java.security.Principal(https)
	 * Locale(���ʻ��йص��������)	InputStream(request.getInputStream();)	OutputStream(response.getOutputStream();)
	 * Reader (request.getReader();)	Writer(response.getWriter();)
	 */
	@RequestMapping("hello05")
	private String handle05(HttpSession session,HttpServletRequest request){
		request.setAttribute("reqParam", "request���еĲ���");
		session.setAttribute("sessionParam", "session���еĲ���");
		return "success";
	}
}
