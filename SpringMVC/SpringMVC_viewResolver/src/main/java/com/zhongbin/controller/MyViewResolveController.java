package com.zhongbin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyViewResolveController {

	@RequestMapping
	public String handle08(Model model){
		
		List<String> carname = new ArrayList<>();
		carname.add("Ferrari");
		model.addAttribute("car", carname);
		return "dabin:/showpicture";
	}
}
