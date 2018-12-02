package com.dangde.service;


import java.util.List;

import com.dangde.domain.Judge;





public interface JudgeService {


	public List<Judge> findByCaseId(Long caseId);


}
