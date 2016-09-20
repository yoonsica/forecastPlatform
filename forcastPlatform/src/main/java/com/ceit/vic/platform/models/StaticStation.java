package com.ceit.vic.platform.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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

	@Id
	private String id;
	private String name;
	private String bz;
	
	public StaticStation(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public StaticStation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
