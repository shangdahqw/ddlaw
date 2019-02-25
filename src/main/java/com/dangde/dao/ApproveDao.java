package com.dangde.dao;

import com.dangde.domain.Approve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApproveDao {

  public boolean insertApprove(Approve approve);

  public boolean updateFlag(Approve approve);

  public List<Approve> getApprove(@Param("material_id") Long materialId);
}
