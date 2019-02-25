package com.dangde.dao;

import com.dangde.domain.Layer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LayerDao {

  public void insertLayer(Layer layer);

  public Long getmaxkey();

  public List<Layer> getByCaseId(@Param("caseId") Long caseId);
}
