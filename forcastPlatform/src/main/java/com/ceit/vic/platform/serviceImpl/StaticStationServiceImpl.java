package com.ceit.vic.platform.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceit.vic.platform.dao.StaticStationDao;
import com.ceit.vic.platform.models.StaticStation;
import com.ceit.vic.platform.models.ZTreeNode;
import com.ceit.vic.platform.service.StaticStationService;
@Service
public class StaticStationServiceImpl implements StaticStationService {
	private static Logger log = Logger.getLogger(StaticStationServiceImpl.class);
	@Autowired
	private StaticStationDao staticStationDao;
	@Override
	public void add(StaticStation station) {
		// TODO Auto-generated method stub
		staticStationDao.add(station);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		staticStationDao.delete(id);
	}

	@Override
	public List<ZTreeNode> findAll() {
		List<ZTreeNode> list1=null;
		List<StaticStation> list = staticStationDao.findAll();
		list1 = new ArrayList<ZTreeNode>();
		ZTreeNode node1 = new ZTreeNode("静态GPS监测列表", 0, 0);
		node1.setNocheck("true");
		list1.add(node1);
		int i=0;
		for(StaticStation station:list){
			ZTreeNode node = new ZTreeNode(station.getName(), ++i, 0);
			node.setNocheck("false");
			list1.add(node);
		}
		return list1;
	}

	@Override
	public void update(StaticStation station) {
		// TODO Auto-generated method stub
		staticStationDao.update(station);
	}

}
