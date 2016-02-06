package com.zy.myeyes.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	private final Log logger = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value={"/index", "/"}, method={RequestMethod.GET})
	public String login(Model model){
		SimpleLog a;
		logger.info("xxxxxxxxxxxxxproccessed by loginxxxxxxxxxxxxxxxxxxxx"
		+logger.getClass()+" "+logger.isDebugEnabled());
		model.addAttribute("msg", "GGGGGGG");
		logger.debug("hahahahahahhahahahah debug");
		return "login.jsp";
	}
}
