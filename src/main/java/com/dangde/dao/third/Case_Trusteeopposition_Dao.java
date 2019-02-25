package com.dangde.dao.third;

import org.apache.ibatis.annotations.Param;

public interface Case_Trusteeopposition_Dao {

  public void insertCase_Trusteeopposition(
      @Param("case_id") Long case_id, @Param("trusteeopposition_id") Long Trusteeopposition_id);
}
