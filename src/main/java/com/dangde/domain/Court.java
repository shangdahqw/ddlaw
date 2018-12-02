package com.dangde.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("Court")

public class Court {
	
	private Long id;
	private String name;
	private String number;
	private String chamber;
	private String address;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getChamber() {
		return chamber;
	}
	public void setChamber(String chamber) {
		this.chamber = chamber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	
	

	
	


}
