package com.ceit.vic.platform.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceit.vic.platform.models.StaticStation;
import com.ceit.vic.platform.models.ZTreeNode;
import com.ceit.vic.platform.service.StaticStationService;

@Controller
public class StaticStationController {
	private static Logger logger = Logger.getLogger(StaticStationController.class);
	
	@Autowired
	private StaticStationService staticStationService;
	
	@RequestMapping("/toStaticStation")
	public ModelAndView toLogin(){
		ModelAndView mav = new ModelAndView("staticStation/staticStation");
		//mav.addObject("errorMsg","");
		return mav;
		
	}
	
	@RequestMapping("/staticStationList")
	@ResponseBody
	public List<ZTreeNode> moduleChild() throws Exception{
		logger.info("staticStationList");
		return staticStationService.findAll();
	}
	
	@RequestMapping(value="/staticStation/add/{stationName}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String add(@PathVariable String stationName) throws Exception{
		logger.info("staticStation/add");
		logger.info(stationName);
		StaticStation station = new StaticStation(stationName,stationName);
		staticStationService.add(station);
		return "OK";
	}
	
}
