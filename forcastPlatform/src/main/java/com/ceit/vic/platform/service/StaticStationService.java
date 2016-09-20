package com.ceit.vic.platform.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ceit.vic.platform.models.StaticStation;
import com.ceit.vic.platform.models.ZTreeNode;
@Transactional
public interface StaticStationService {
	public void add(StaticStation station);
	public void delete(String id);
	public List<ZTreeNode> findAll();
	public void update(StaticStation station);
}
