package com.dangde.dao;

import com.dangde.domain.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialDao {

  public Material getMaterial(@Param("id") Long material_id);

  public boolean insertMaterial(Material material);

  public Long getmaxid();

  public boolean material_Output(@Param("id") Long id);

  // 得到用户指定operation_id下的材料列表
  public List<Material> getMaterials(
      @Param("operation_id") Long operation_id,
      @Param("material_type") int material_type,
      @Param("startindex") int startindex,
      @Param("pagesize") int pagesize);

  public long getTotalrecord(
      @Param("operation_id") Long operation_id, @Param("material_type") int material_type);

  // 获取之前输出成果的材料id
  public Long material_Cancel_Output_getid(
      @Param("operation_id") Long operation_id, @Param("type") int type);

  // 更新操作
  public boolean material_Cancel_Output(@Param("id") Long id);

  // 得到对应operation_id的成果输出
  public List<Material> getMaterials_Output(@Param("operation_id") Long operation_id);

  public boolean material_delete_Output(@Param("material_ids") List<Long> material_ids);
}
