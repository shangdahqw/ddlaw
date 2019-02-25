package com.dangde.service.impl;

import com.dangde.dao.LayerDao;
import com.dangde.domain.Layer;
import com.dangde.service.LayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("layerService")
public class LayerServiceImpl implements LayerService {

  @Autowired private LayerDao layerDao;

  @Override
  public List<Layer> findByCaseId(Long caseId) {

    return this.layerDao.getByCaseId(caseId);
  }
}
