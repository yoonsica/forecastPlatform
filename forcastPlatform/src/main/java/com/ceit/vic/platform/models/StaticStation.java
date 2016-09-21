package com.ceit.vic.platform.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 静态GPS监测点类
 * @author 011371985
 *
 */
@Entity
@Table
public class StaticStation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912971111079113038L;

	
	private int stationId;
	private String name;
	private String bz;
	private Set<StaticGpsData> data;
	public StaticStation(String name) {
		this.name = name;
	}
	public StaticStation() {
	}
	@Id
	@GeneratedValue
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@OneToMany(mappedBy="station",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<StaticGpsData> getData() {
		return data;
	}
	public void setData(Set<StaticGpsData> data) {
		this.data = data;
	}
	
	
}
