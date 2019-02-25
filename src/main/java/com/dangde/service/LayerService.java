package com.dangde.service;

import com.dangde.domain.Layer;

import java.util.List;

public interface LayerService {

  public List<Layer> findByCaseId(Long caseId);
}
