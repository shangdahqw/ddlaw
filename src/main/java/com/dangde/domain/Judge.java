package com.dangde.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("Judge")

public class Judge {
	
	private Long id;
	private String name;
	private String tel;
	private int main;

	
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getMain() {
		return main;
	}
	public void setMain(int main) {
		this.main = main;
	}

	
	


}
