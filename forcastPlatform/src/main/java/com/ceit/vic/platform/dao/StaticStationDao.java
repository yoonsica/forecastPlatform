package com.ceit.vic.platform.dao;

import java.util.List;

import com.ceit.vic.platform.models.StaticStation;

public interface StaticStationDao {
	public void add(StaticStation station);
	public void delete(int id);
	public List<StaticStation> findAll();
	public void update(StaticStation station);
	StaticStation findById(int id);
}
