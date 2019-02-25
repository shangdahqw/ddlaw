package com.dangde.domain.model;

import com.dangde.domain.Judge;

import java.util.List;

public class Judges {

  private List<Judge> judges;

  public List<Judge> getJudges() {
    return judges;
  }

  public void setJudges(List<Judge> Judges) {
    this.judges = Judges;
  }

  public Judges(List<Judge> judges) {
    super();
    this.judges = judges;
  }

  public Judges() {
    super();
  }
}
