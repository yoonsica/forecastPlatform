package com.ceit.vic.platform.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ceit.vic.platform.dao.StaticStationDao;
import com.ceit.vic.platform.models.StaticStation;

@Repository
public class StaticStationDaoImpl implements StaticStationDao{

	private static Logger log = Logger.getLogger(StaticStationDaoImpl.class);
	@Autowired
	private SessionFactory sf;

	@Override
	public void add(StaticStation station) {
		// TODO Auto-generated method stub
		log.info("add StaticStation");
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		log.info("delete StaticStation");
	}

	@Override
	public List<StaticStation> findAll() {
		// TODO Auto-generated method stub
		log.info("findAll StaticStation");
		Query query = sf.getCurrentSession().createQuery("from StaticStation");
		return query.list();
	}

	@Override
	public void update(StaticStation station) {
		// TODO Auto-generated method stub
		log.info("update StaticStation");
	}
}
