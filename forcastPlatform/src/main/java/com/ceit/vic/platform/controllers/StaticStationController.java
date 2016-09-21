package com.ceit.vic.platform.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ceit.vic.platform.models.StaticGpsData;
import com.ceit.vic.platform.models.StaticGpsDataDTO;
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
		StaticStation station = new StaticStation(stationName);
		staticStationService.add(station);
		return "OK";
	}
	
	@RequestMapping(value="/staticStation/update/{stationId}/{StationName}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String update(@PathVariable String stationId,@PathVariable String StationName) throws Exception{
		logger.info("staticStation/update");
		logger.info(stationId+"|"+StationName);
		StaticStation station = staticStationService.getById(stationId);
		station.setName(StationName);
		staticStationService.update(station);
		return "OK";
	}
	
	@RequestMapping(value="/staticStation/remove/{stationId}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String remove(@PathVariable String stationId) throws Exception{
		logger.info("staticStation/remove");
		logger.info(stationId);
		staticStationService.delete(stationId);
		return "OK";
	}
	
	@RequestMapping("/staticStation/toData/{stationId}")
	public ModelAndView toData(@PathVariable String stationId){
		ModelAndView mav = new ModelAndView("staticStation/data");
		mav.addObject("staticStationId",stationId);
		return mav;
		
	}
	
	@RequestMapping(value="/staticStation/data/{stationId}")
	@ResponseBody
	public Map<String,Object> data(@PathVariable String stationId) throws Exception{
		logger.info("staticStation/data");
		logger.info(stationId);
		List<StaticGpsDataDTO> dataList = new ArrayList<StaticGpsDataDTO>();
		StaticGpsDataDTO data = new StaticGpsDataDTO("20140401",1,98.9,-88.5,132.71);
		dataList.add(data);
		int total = 1;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", dataList);
		return map;
	}
}
