package com.ceit.vic.platform.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 静态GPS站点数据
 * @author 011371985
 *
 */
@Entity
@Table
public class StaticGpsData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3945875093464662078L;
	
	private String datadate;
	private int day;
	private double x;
	private double y;
	private double xy;
	private StaticStation station;
	
	public StaticGpsData() {
	}
	public StaticGpsData(String datadate, int day, double x, double y, double xy, StaticStation station) {
		this.datadate = datadate;
		this.day = day;
		this.x = x;
		this.y = y;
		this.xy = xy;
		this.station = station;
	}
	@Id
	public String getDatadate() {
		return datadate;
	}
	public void setDatadate(String datadate) {
		this.datadate = datadate;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getXy() {
		return xy;
	}
	public void setXy(double xy) {
		this.xy = xy;
	}
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=StaticStation.class)
	@JoinColumn(name="stationId")
	public StaticStation getStation() {
		return station;
	}
	public void setStation(StaticStation station) {
		this.station = station;
	}
	
}