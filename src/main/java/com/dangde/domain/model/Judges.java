package com.dangde.domain.model;

import java.util.List;

import com.dangde.domain.Judge;

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
