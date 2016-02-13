package com.zy.myeyes.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zy.myeyes.beans.User;
import com.zy.myeyes.validatores.UserValidator;


@Controller
public class LoginController {
	private final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.addValidators(userValidator);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	@ModelAttribute("className")
	private String setModel(){
		return this.getClass().getName();
	}
	
	@ModelAttribute
	private void setModel1(Model model){
		model.addAttribute("teacher", "haha");
	}
	
	@ModelAttribute
	private String setModel2(){
		return "return";
	}
	
	@RequestMapping(value={"/user"}, method={RequestMethod.GET})
	public String user(Date date, String time, ModelMap modelMap){
		logger.debug("xxxxxxxxxxxxxxxxxx processed by user xxxxxxxxxxxxxxxxxxxxx");
		logger.debug("params date:"+date+" time:"+time);
		logger.debug("modelMap :"+modelMap);
		
		
		modelMap.addAttribute("user", new User());
		return "user.jsp";
	}
	
	
	@RequestMapping(value={"/login"}, method={RequestMethod.POST})
	public String login(@Validated User user, BindingResult br, RedirectAttributes ra, ModelMap modelMap){
		logger.debug("xxxxxxxxxxxxxxxxxx processed by login xxxxxxxxxxxxxxxxxxxxx");
		logger.debug("user :"+user.getUserName());
		
		logger.debug("modelMap :"+modelMap);
		ra.addFlashAttribute("user", user);		
		return "user.jsp";
	}
}
