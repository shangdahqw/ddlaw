package com.dangde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dangde.dao.TrusteeoppositionDao;
import com.dangde.domain.Trusteeopposition;
import com.dangde.service.TrusteeoppositionService;

@Service("trusteeoppositionService")
public class TrusteeoppositionServiceImpl implements TrusteeoppositionService {

	@Autowired
	private TrusteeoppositionDao trusteeoppositionDao;
	
	@Override
	public List<Trusteeopposition> findByCaseId(Long caseId) {

		return this.trusteeoppositionDao.getByCaseId(caseId);
	}

}
