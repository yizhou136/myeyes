package com.zy.myeyes.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zy.myeyes.beans.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.context.support.ServletContextLiveBeansView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes(value={"book"}, types={Integer.class})
public class IndexController {
	private static final Log logger = LogFactory.getLog(IndexController.class);

	@RequestMapping(value={"/index", "/"}, method={RequestMethod.GET})
	@ResponseBody
	public void index(Map map, ModelMap modelMap, Model model, @RequestParam(defaultValue = "ahha default") String name, HttpSession httpSession){
		logger.debug("xxxxxxxxxxxxxxxxxx processed by index 1234 afasdfasdfsadf xxxxxxxxxxxxxxxxxxxxx");
		logger.debug("modelMap :"+modelMap+" requestMap:"+map);
		
		//modelMap.addAttribute("msg", "haha googogogog");
		//model.addAttribute("msg", "haha googogogog");
		//model.addAttribute("book", "jinggangjin");

		User user = new User();
		user.setAge(11);
		user.setName("zy");
		//return "redirect:hello";
		/*Map<String,String> returnMap = new HashMap<String,String>();
		returnMap.put("name", "zy");
		returnMap.put("age", "18");*/
		//return "redirect:hello";

		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		//com.fasterxml.jackson.core
		ObjectMapper objectMapper;
		model.addAttribute("xmlModelKey", list);
		model.addAttribute("name", name);

		httpSession.setAttribute("user", user);
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

	@RequestMapping("/testcallable")
	public Callable<String> testCallable(final HttpServletResponse response, final HttpServletRequest request){
		logger.debug("xxxxxxxxxxxxxxxxxx processed by testCallable xxxxxxxxxxxxxxxxxxxxx");
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				try {
					logger.debug("xxxxxxxxxxxxxxxxxx do  calling xxxxxxxxxxxxxxxxxxxxx");
					response.setContentType("text/plain;charset=utf-8");
					response.getWriter().write("nihao");
					//response.getWriter().close();
					request.getAsyncContext().complete();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
