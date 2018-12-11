package com.dangde.domain.model;

import com.dangde.domain.Trusteeopposition;

import java.util.List;

public class Trusteeoppositions {

  private List<Trusteeopposition> trusteeoppositions;

  public List<Trusteeopposition> getTrusteeoppositions() {
    return trusteeoppositions;
  }

  public void setTrusteeoppositions(List<Trusteeopposition> trusteeoppositions) {
    this.trusteeoppositions = trusteeoppositions;
  }

  public Trusteeoppositions(List<Trusteeopposition> trusteeoppositions) {
    super();
    this.trusteeoppositions = trusteeoppositions;
  }

  public Trusteeoppositions() {
    super();
  }
}
