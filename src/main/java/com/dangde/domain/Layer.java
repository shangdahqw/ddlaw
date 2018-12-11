package com.dangde.domain;

import org.apache.ibatis.type.Alias;

@Alias("Layer")
public class Layer {

  private Long id;
  private String name;
  private String tel;
  private int main;
  private String firm;
  private String number;

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

  public String getFirm() {
    return firm;
  }

  public void setFirm(String firm) {
    this.firm = firm;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
}
