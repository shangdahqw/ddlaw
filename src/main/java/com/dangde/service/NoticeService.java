package com.dangde.service;

import com.dangde.domain.Notice;

import java.util.List;

public interface NoticeService {

  public List<Notice> findAll(Long userId);

  public List<Notice> findAllshenhe(Long userId);

  public void updateById(Long id);
}
