package com.dangde.dao.third;

import org.apache.ibatis.annotations.Param;

public interface Case_Layer_Dao {

  public void insertCase_Layer(@Param("case_id") Long case_id, @Param("layer_id") Long layer_id);
}
