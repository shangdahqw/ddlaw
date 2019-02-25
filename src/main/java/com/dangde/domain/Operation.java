package com.dangde.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Operation")
public class Operation {

  private Long id;
  private String name;
  private Date time;
  private Long case_id;
  private int type;

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

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Long getCase_id() {
    return case_id;
  }

  public void setCase_id(Long case_id2) {
    this.case_id = case_id2;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
