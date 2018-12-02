package com.dangde.domain.model;

import java.util.List;

import com.dangde.domain.Operation;

public class Operations {

	
	private List<Operation> operations;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Operations(List<Operation> operations) {
        super();
        this.operations = operations;
    }

    public Operations() {
        super();
    }

}
