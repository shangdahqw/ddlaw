package com.dangde.dao;

import com.dangde.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeDao {

  public boolean insert_Notice(Notice notice);

  public List<Notice> findAll(@Param("userId") Long userId);

  public List<Notice> findAllshenhe(@Param("userId") Long userId);

  public void updateById(@Param("id") Long id);
}
