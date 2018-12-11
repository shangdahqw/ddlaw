package com.dangde.service;

import com.dangde.domain.Approve;
import com.dangde.domain.Comment;
import com.dangde.domain.Material;
import com.dangde.domain.model.QueryInfo;
import com.dangde.domain.model.XQ_approveVO;
import com.dangde.domain.model.XQ_commentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialService {

  public Material getMaterial(Long material_id);

  public boolean insertMaterial(
      Material material,
      MultipartFile[] files,
      String dir,
      List<Long> userids_coopandcreate,
      Long userid_create,
      String casesummery,
      String username,
      Long caseId);

  public boolean material_Output(Long material_id, Long operation_id, int type);

  public boolean send_Material(
      List<Long> user_ids,
      Long material_id,
      Long caseId,
      String casesummery,
      String username,
      Long operation_id);

  public boolean insert_Comment(Comment comment);

  public boolean approve(Approve approve);

  public List<XQ_approveVO> getApproveXiangQing(Long material_id);

  public List<XQ_commentVO> getCommentXiangQing(Long material_id);

  // 得到对应operation_id的成果输出
  public List<Material> getMaterials_Output(Long operation_id, Long userId);

  public boolean output_Delete(List<Long> material_ids);

  public List<Object> getMaterials(
      Long operation_id, int material_type, Long userid, QueryInfo queryInfo);
}
