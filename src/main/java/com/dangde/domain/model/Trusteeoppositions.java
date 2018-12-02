package com.dangde.domain.model;

import java.util.List;

import com.dangde.domain.Trusteeopposition;

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
