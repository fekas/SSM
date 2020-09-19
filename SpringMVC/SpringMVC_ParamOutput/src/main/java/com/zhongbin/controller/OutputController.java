package com.zhongbin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring�����ݴ���ҳ��
 * 	>��HttpRequest��ԭ��API
 * 	>����Map����Model����ModelMap	���������������
 * 		��ϵ��Map��Model��ModelMap���ն���org.springframework.validation.support.BindingAwareModelMap
 * 			�ڹ�������BindingAwareModelMap�б���Ķ����������������С�
 * 			�̳й�ϵ��
 * 			Map	(jdk�ṩ��interface)				Model(Spring�ṩ��interface)				
 * 			||									//
 * 			||	ʵ��							   //
 * 			\/								  //
 * 		  ModelMap(Class)					 //ʵ��
 * 						\\					//
 *						 \\	�̳�			   // 						
 *						  \\			  //
 * 						   ExtendedModelMap
 * 									||	�̳�							
 * 									\/
 * 							BindingAwareModelMap
 * 	>���÷����ķ���ֵ:ModelAndView���ͷ���ֵ 	���ݷ�����������
 *	>��ʱ��session���б�������
 *		1����@SessionAttributes	ֻ�ܱ�������	���ܻ�����쳣�������HandlerMethodInvoker�е�resolveModelAttribute����
 *			String[] value() default {};
 *			Class<?>[] types() default {};
 *		2�����Ƽ���ԭ��API
 * @author XiaoBin
 * 2020��9��17������3:09:19
 */
@SessionAttributes(value="QAQ",types={Integer.class})//��Map����Model����Model����ModelAndView�б�������ݸ�session��Ҳ��һ�ݣ�
								//valueָ��key����,types={Integer.class}ָ�����ͱ���
@Controller
public class OutputController {

	@RequestMapping("/handle01")
	public String handle01(Map<String,Object> map) {
		map.put("QAQ", "AQA-map");
		System.out.println("Map������"+map.getClass());
		return "success";
	}
	@RequestMapping("/handle02")
	public String handle02(Model model) {
		model.addAttribute("QAQ","AQA-model");
		System.out.println("Model������"+model.getClass());
		return "success";
	}
	@RequestMapping("/handle03")
	public String handle03(ModelMap modelMap) {
		modelMap.addAttribute("QAQ","AQA-modelMap");
		System.out.println("ModelMap������"+modelMap.getClass());
		return "success";
	}
	
	@RequestMapping("/handle04")
	public ModelAndView handle04() {
		//ԭ�����Ƿ��ص�success����ͼ������ͼ���������Զ������ǽ���ƴ���õ�ҳ������ǵ�ַ
		ModelAndView modelAndView = new ModelAndView("success");
		modelAndView.addObject("QAQ","AQA-modelAndView");
		return modelAndView;
	}
	@RequestMapping("/handle05")
	public String handle05(String string) {
		
		System.out.println(string);
		return "success";
	}
}
