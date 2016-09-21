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
		log.info("add StaticStation");
		sf.getCurrentSession().save(station);
	}

	@Override
	public void delete(int id) {
		log.info("delete StaticStation "+id);
		StaticStation station = findById(id);
		if(null!=station){
			log.info("**************");
			sf.getCurrentSession().delete(findById(id));
		}
	}

	@Override
	public List<StaticStation> findAll() {
		log.info("findAll StaticStation");
		Query query = sf.getCurrentSession().createQuery("from StaticStation");
		return query.list();
	}

	@Override
	public void update(StaticStation station) {
		log.info("update StaticStation");
		sf.getCurrentSession().update(station);
	}
	@Override
	public StaticStation findById(int id){
		return (StaticStation) sf.getCurrentSession().get(StaticStation.class, id);
	}
}
