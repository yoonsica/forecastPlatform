package com.ceit.vic.platform.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ceit.vic.platform.models.ZTreeNode;

@Controller
public class LoginController {
	static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(){
		ModelAndView mav = new ModelAndView("login");
		//mav.addObject("errorMsg","");
		return mav;
		
	}
	
	@RequestMapping(value="/valid",method=RequestMethod.POST)
	public ModelAndView valid(HttpSession session,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("south");
		return mav;
	}
	
	@RequestMapping("/moduleIdPass/{moduleId}")
	public ModelAndView moduleIdPass(@PathVariable int moduleId){
		logger.info("moduleIdPass");
		ModelAndView mav = new ModelAndView("westDiv");
		//List<ZTreeNode> nodeList=resourcesService.getResourcesTreeByParentId(id);
		//mav.addObject("nodeList",nodeList);
		mav.addObject("moduleId",moduleId);
		return mav;
	}
	
	@RequestMapping("/moduleChild/{parentId}")
	@ResponseBody
	public List<ZTreeNode> moduleChild(@PathVariable int parentId,HttpServletRequest request) throws Exception{
		logger.info("moduleChild");
		List<ZTreeNode> childList = new ArrayList<ZTreeNode>();
		ZTreeNode node1 = new ZTreeNode("功能列表", 0, 0);
		ZTreeNode node11 = new ZTreeNode("静态GPS监测点", 1, 0);
		node11.setHref("toStaticStation");
		ZTreeNode node12 = new ZTreeNode("实时GPS监测点", 2, 0);
		childList.add(node1);
		childList.add(node11);
		childList.add(node12);
		return childList;
	}
}
