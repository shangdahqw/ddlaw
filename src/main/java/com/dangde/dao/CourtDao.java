package com.dangde.dao;

import com.dangde.domain.Court;
import org.apache.ibatis.annotations.Param;

public interface CourtDao {

  public void insertCourt(Court court);

  public Long getmaxkey();

  public Court getById(@Param("id") Long id);
}
