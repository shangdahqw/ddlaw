package com.dangde.service;

import java.util.List;

import com.dangde.domain.Trusteeopposition;



public interface TrusteeoppositionService {

	public List<Trusteeopposition> findByCaseId(Long caseId);

}
