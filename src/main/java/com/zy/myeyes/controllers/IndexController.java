package com.zy.myeyes.controllers;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes(value={"book"}, types={Integer.class})
public class IndexController {
	private static final Log logger = LogFactory.getLog(IndexController.class);

	@RequestMapping(value={"/index", "/", ""}, method={RequestMethod.GET})
	public String index(Map map, ModelMap modelMap, Model model){
		logger.debug("xxxxxxxxxxxxxxxxxx processed by index xxxxxxxxxxxxxxxxxxxxx");		
		logger.debug("modelMap :"+modelMap+" requestMap:"+map);
		
		modelMap.addAttribute("msg", "haha googogogog");
		model.addAttribute("msg", "haha googogogog");
		model.addAttribute("book", "jinggangjin");
		
		
		return "redirect:hello";
	}
	
	@RequestMapping(value={"/hello"}, method={RequestMethod.GET})
	//@ResponseBody	
	public String index(Model model, SessionStatus status) throws Exception{
		logger.debug("xxxxxxxxxxxxdebugdebugdebugdebugdebugdebugdebugdebugdebugxxxxxxxxxxx");
		logger.info("===========processed by hello =============");
				
		logger.debug("modelMap :"+model.asMap());
		
		model.addAttribute("msg", "Go Go Go!");
		status.setComplete();
		return "redirect:after";
	}
	
	@RequestMapping(value={"/after", }, method={RequestMethod.GET})
	public String after(ModelMap modelMap){		
		logger.debug("xxxxxxxxxxxxxxxxxx processed by after xxxxxxxxxxxxxxxxxxxxx");		
		logger.debug("modelMap :"+modelMap);
		
		modelMap.addAttribute("msg", "haha googogogog");		
		return "index.jsp";
	}
}
