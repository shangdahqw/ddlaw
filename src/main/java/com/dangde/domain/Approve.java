package com.dangde.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Approve")
public class Approve {

  private Long id;
  private Long material_id;
  private Long user_id;
  private int flag;
  private String note;
  private Date create_time;

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

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }
}
