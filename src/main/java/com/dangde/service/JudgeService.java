package com.dangde.service;

import com.dangde.domain.Judge;

import java.util.List;

public interface JudgeService {

  public List<Judge> findByCaseId(Long caseId);
}
