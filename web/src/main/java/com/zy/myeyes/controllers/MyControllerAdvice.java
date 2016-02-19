package com.zy.myeyes.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages={"com.zy.myeyes.controllers"})
public class MyControllerAdvice {

	@ModelAttribute("msg")
	private String setCommonModel(){
		return "common msg";
	}
	
	
	@ModelAttribute("commonModel2")
	private String setCommonModel2(){
		return "commonModel2";
	}
	
	@ModelAttribute
	private void setCommonModel1(Model model){
		model.addAttribute("commonModel1", "commonModel1");
	}
}
