package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	/**
	 * 视图解析器解析后转发到WEB-INF/pages/success.jsp
	 */
	@RequestMapping("/hello")
	public String hello(){
		return "success";
	}
	
	/**
	 * 返回到其他路径的jsp页面?
	 * 	>用相对路径
	 * 	>用forward:/			forward前缀的转发不会由试图解析器拼串
	 * @return
	 */
	@RequestMapping("/handle01")
	public String handle01(){
		return "../../hello";
	}
	@RequestMapping("/handle02")
	public String handle02(){
		return "forward:/hello.jsp";		//转发到当前项目下的hello.jsp(注意加上"/"表示项目根路径，不加表示相对路径易出错)
	}
	@RequestMapping("/handle03")
	public String handle03(){
		return "forward:/handle02";			//转向其他请求
	}
	/**
	 * 重定向到其他页面
	 * 
	 * redirect:重定向路径
	 * 	原生Servlet重定向：
	 * 	reponse.sendRedirect("/hello.jsp")
	 * @return
	 */
	@RequestMapping("/handle04")
	public String handle04(){
		return "redirect:/hello.jsp";
	}
	@RequestMapping("/handle05")
	public String handle05(){
		return "redirect:/handle04";		//多次重定向
	}
	
	/**
	 * JstlView支持快速国际化
	 * 
	 * JavaWeb的国际化
	 * 	>得到一个locale对象
	 * 	>使用ResourceBundle绑定国际化资源文件
	 * 	>使用ResourceBund.getString("key");获取国际化配置文件中的值
	 * 	>web页面国际化：使用fmt标签
	 * 		<fmt:setLocal>
	 * 		<fmt:setBundle>
	 * 		<fmt:message>
	 * 
	 * 
	 * 使用JstlView
	 * 	>让Spring管理国际化资源
	 * 	>在页面使用<fmt:message>取值即可
	 * 
	 * 
	 * 如果返回值有forward:前缀视图解析器就不会创建JstlView，则不能使用快速国际化功能
	 * 原因见：org.springframework.web.servlet.view.UrlBasedViewResolver.createView(String, Locale)方法
	 * 		当视图名带前缀的时候会创建InternalResourceView所以不能使用快速国际化
	 * @return
	 */
	@RequestMapping("/handle06")
	public String handle06(){
		return "login";
	}
}
