package com.dangde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangde.dao.CourtDao;
import com.dangde.domain.Court;
import com.dangde.service.CourtService;

@Service("courtService")

public class CourtServiceImpl implements CourtService {

	@Autowired
	private CourtDao courtDao;
	@Override
	public Court findByid(Long courtId) {
		
		return courtDao.getById(courtId);
	}

}
