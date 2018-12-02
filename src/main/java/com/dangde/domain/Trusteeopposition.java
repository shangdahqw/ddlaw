package com.dangde.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("Trusteeopposition")

public class Trusteeopposition {

	
	private Long id;
	private int identity;
	private int type;
	private String person_name;
	private int sex;
	private Date person_birthday;
	private String person_adress;
	private String person_id_number;
	private String company_name;
	private String company_address;
	private String company_represent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getPerson_birthday() {
		return person_birthday;
	}
	public void setPerson_birthday(Date person_birthday) {
		this.person_birthday = person_birthday;
	}
	public String getPerson_adress() {
		return person_adress;
	}
	public void setPerson_adress(String person_adress) {
		this.person_adress = person_adress;
	}
	public String getPerson_id_number() {
		return person_id_number;
	}
	public void setPerson_id_number(String person_id_number) {
		this.person_id_number = person_id_number;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_represent() {
		return company_represent;
	}
	public void setCompany_represent(String company_represent) {
		this.company_represent = company_represent;
	}


}
