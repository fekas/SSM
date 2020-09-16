package com.zhongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1.���@Controller��ʾ�������һ��������
 * @author XiaoBin
 * 2020��9��4������11:39:16
 * 
 * �������̣�
 * 	>�ͻ��˵����ⷢ��hello����
 * 	>springMVC��ǰ�˿������յ���������
 * 	>�ж��յ��������@RequestMapping��ע���ĸ�����ƥ��
 * 	>ǰ�˿������ҵ���Ŀ�괦�������Ŀ�귽�����÷���ִ�и÷���
 * 	>springMVC��Ϊ����ֵ������ת��ҳ���ַ
 * 	>����ͼ������ƴ���õ������ĵ�ַ
 * 	>springMVCת������ҳ��
 * 
 * @RequestMapping������SpringMVC���������������ʲô����
 * @Target({ElementType.METHOD, 	д�����ϣ�Ϊ��ǰ����������������ṩһ����׼·��
 * ElementType.TYPE					д�ڷ����ϣ�
 * })
 * String[] value() default {};
 * RequestMethod[] method() default {};		�޶�����ʽ(get/post��),Ĭ��ȫ���ܡ�
 * String[] params() default {};			�޶��������֧��"!,&&,||��" eg��params={"username"},������������Ϊusername�Ĳ���
 * String[] headers() default {};			�����޶�����ͷ
 * String[] consumes() default {};
 * String[] produces() default {};
 * ģ��ƥ�书�ܣ�
 * ?����һ���ַ�
 * *����һ�������ַ���һ��·��
 * **������·��
 * Խ��ȷ���ȼ�Խ��
 *
 */
@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String myFirstRequest() {
		System.out.println("HelloController�յ�hello����");
		
	//	return "/WEB-INF/pages/success.jsp";
		//����ͼ�������Զ�ƴ����prefix+return+suffix
		return "success";
	}
	@RequestMapping("/hello*")
	public String helloMyAnt(){
		
		return "success";
		
	}
	
	//·���ϻ�����дռλ����ռλ�����﷨�ǿ���������·���ĵط�дһ��{������}
	//·���ϵ�ռλ��ֻ��ռһ��·��
	@RequestMapping("/hello*/{id}")
	public String hiMyAnt(@PathVariable("id")String id){
		System.out.println(id);
		return "success";
		
	}
}
