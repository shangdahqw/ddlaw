package com.dangde.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Case;
import com.dangde.domain.Notice;

public interface NoticeDao {
	
	public boolean insert_Notice(Notice notice);
	public List<Notice> findAll(@Param("userId")Long userId);
	public List<Notice> findAllshenhe(@Param("userId")Long userId);
	public void updateById(@Param("id")Long id);
}
