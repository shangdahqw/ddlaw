package com.dangde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangde.dao.NoticeDao;
import com.dangde.domain.Notice;
import com.dangde.service.NoticeService;

@Service("noticeService")

public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	@Override
	public List<Notice> findAll(Long userId) {
		
		return this.noticeDao.findAll(userId);
	}
	@Override
	public List<Notice> findAllshenhe(Long userId) {
		
		return this.noticeDao.findAllshenhe(userId);
	}
	@Override
	public void updateById(Long id) {
		this.noticeDao.updateById(id);
		
	}

}
