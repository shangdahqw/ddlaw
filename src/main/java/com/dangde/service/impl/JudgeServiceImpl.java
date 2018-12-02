package com.dangde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangde.dao.CourtDao;
import com.dangde.dao.JudgeDao;
import com.dangde.domain.Judge;
import com.dangde.service.JudgeService;

@Service("judgeService")
public class JudgeServiceImpl implements JudgeService {

	@Autowired
	private JudgeDao judgeDao;
	@Override
	public List<Judge> findByCaseId(Long caseId) {
		
		return this.judgeDao.getByCaseId(caseId);
	}

}
