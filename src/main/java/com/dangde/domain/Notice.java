package com.dangde.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Notice")

public class Notice {
	
	private Long id;
	private Long material_id;
	private int  type; //0为上传，1为发送
	private int  flag;//0未查看 ，1已查看
	private Long case_id;
	private Long user_id;
	private Long operation_id;
	private Date create_time;
	private  String content;
	public String casesummery;
	
	
	
	
	public String getCasesummery() {
		return casesummery;
	}
	public void setCasesummery(String casesummery) {
		this.casesummery = casesummery;
	}


	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(Long material_id) {
		this.material_id = material_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getCase_id() {
		return case_id;
	}
	public void setCase_id(Long case_id) {
		this.case_id = case_id;
	}
	public Long getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(Long operation_id) {
		this.operation_id = operation_id;
	}
	
	
}
