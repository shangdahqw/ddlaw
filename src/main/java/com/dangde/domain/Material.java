package com.dangde.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("Material")

public class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7314638183374386530L;

	private Long id;
	private Long operation_id;
	private Long user_id;
	private int type;
	private Date create_time;
	private String title;
	private String introduce;
	private String url;
	private int flag_output;
	private int file_type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(Long operation_id) {
		this.operation_id = operation_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFlag_output() {
		return flag_output;
	}
	public void setFlag_output(int flag_output) {
		this.flag_output = flag_output;
	}
	public int getFile_type() {
		return file_type;
	}
	public void setFile_type(int file_type) {
		this.file_type = file_type;
	}
	
}
