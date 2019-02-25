package com.dangde.service;

import com.dangde.domain.Trusteeopposition;

import java.util.List;

public interface TrusteeoppositionService {

  public List<Trusteeopposition> findByCaseId(Long caseId);
}
