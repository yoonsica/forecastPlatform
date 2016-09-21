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
public class StaticGpsDataDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3945875093464662078L;
	
	private String datadate;
	private int day;
	private double x;
	private double y;
	private double xy;
	public StaticGpsDataDTO() {
	}
	public StaticGpsDataDTO(String datadate, int day, double x, double y, double xy) {
		this.datadate = datadate;
		this.day = day;
		this.x = x;
		this.y = y;
		this.xy = xy;
	}
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
}