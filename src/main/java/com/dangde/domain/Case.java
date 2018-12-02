package com.dangde.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("Case")

public class Case {

	
	private Long id;
	private String level;//审级
	private String summary;//案由
	private Date creattime;
	private Date opentime;//开庭时间
	private Date termtime;//举证期限
	private Long court_id;

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public Date getOpentime() {
		return opentime;
	}
	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}
	public Date getTermtime() {
		return termtime;
	}
	public void setTermtime(Date termtime) {
		this.termtime = termtime;
	}
	public Long getCourt_id() {
		return court_id;
	}
	public void setCourt_id(Long court_id) {
		this.court_id = court_id;
	}





}
