package com.dangde.web;

import com.dangde.config.Configdangde;
import com.dangde.domain.*;
import com.dangde.domain.model.QueryInfo;
import com.dangde.domain.model.XQ_approveVO;
import com.dangde.domain.model.XQ_commentVO;
import com.dangde.responseModle.ResultData;
import com.dangde.service.CaseService;
import com.dangde.service.MaterialService;
import com.dangde.service.NoticeService;
import com.dangde.service.UserService;
import com.dangde.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
// @RequestMapping("/case/{caseId}/operation/{operation_id}")
@RequestMapping("/material")
public class MaterialController {

  @Autowired MaterialService materialService;
  @Autowired CaseService caseService;
  @Autowired UserService userService;
  @Autowired NoticeService noticeService;

  // 材料列表
  @GetMapping(value = "/materials")
  @ResponseBody
  public ResultData<Map<String, Object>> showmaterials(
      Long operation_id,
      int operation_type,
      int material_type,
      Integer currentpage,
      HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Long user_id = (Long) request.getAttribute("user_id");

    QueryInfo queryInfo = new QueryInfo();
    if (currentpage != null) {
      queryInfo.setCurrentpage(currentpage);
    }
    List<Object> materialsAndPagebean =
        materialService.getMaterials(operation_id, material_type, user_id, queryInfo);

    map.put("operation_id", operation_id);
    map.put("operation_type", operation_type);
    map.put("materialsAndPagebean", materialsAndPagebean);
    map.put("material_type", material_type);

    if (material_type == 100) {
      map.put("ViewName", "materialUI");

    } else {
      map.put("ViewName", "materialUI2");
    }
    resultData.setData(map);
    resultData.setStatus(Constant.SUCCESS_DATA);
    resultData.setMessage("正常响应数据");
    return resultData;
  }

  // 材料详情
  @GetMapping(value = "/{id}")
  @ResponseBody
  public ResultData<Map<String, Object>> getmaterial(@PathVariable("id") Long material_id) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Material material = materialService.getMaterial(material_id);
    List<XQ_commentVO> xQ_commentVOs = materialService.getCommentXiangQing(material_id);
    List<XQ_approveVO> xQ_approveVOs = materialService.getApproveXiangQing(material_id);

    map.put("material", material);
    map.put("xQ_commentVOs", xQ_commentVOs);
    map.put("xQ_approveVOs", xQ_approveVOs);

    if (material.getFile_type() == 0) map.put("ViewName", "detail_picture");
    else if (material.getFile_type() == 1) map.put("ViewName", "detail_document");

    resultData.setData(map);
    resultData.setStatus(Constant.SUCCESS_DATA);
    resultData.setMessage("正常响应数据");
    return resultData;
  }

  // 材料上传,只跟协作者有关
  @PostMapping()
  @ResponseBody
  public ResultData<Map<String, Object>> upload(
      Material material, Long case_id, MultipartFile[] files, HttpServletRequest request) {
    // 前段发过来的是title,introduce,file_type,flag_output,type
    // file_type: 0代表图片上传    1代表文档上传

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    String dir = "";

    if (material.getFile_type() == 0) dir = Configdangde.UPLOAD_PATH_IMAGE;
    else if (material.getFile_type() == 1) dir = Configdangde.UPLOAD_PATH_DOCUMENT;

    Long user_id = (Long) request.getAttribute("user_id");
    User user = userService.getUserById(user_id);
    List<Long> userids_coopandcreate =
        caseService.selectCoopidsAndCreateids(case_id); // 得到所有协作者和创建者的用户id

    Case casee = this.caseService.findByid(case_id);

    try {
      materialService.insertMaterial(
          material,
          files,
          dir,
          userids_coopandcreate,
          user_id,
          casee.getSummary(),
          user.getUsername(),
          casee.getId());

      resultData.setMessage("上传成功");
      resultData.setStatus(Constant.UPLOAD_SUCCESS);

    } catch (Exception e) {
      resultData.setMessage("上传失败");
      resultData.setStatus(Constant.UPLOAD_ERROR);
    } finally {
      return resultData;
    }
  }

  // 发送材料
  @PostMapping("/{id}/send")
  @ResponseBody
  public ResultData<Map<String, Object>> send_Material(
      @RequestBody List<Long> user_ids,
      @PathVariable("id") Long material_id,
      Long case_id,
      Long operation_id,
      HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Long user_id = (Long) request.getAttribute("user_id");
    User user = userService.getUserById(user_id);
    Case casee = this.caseService.findByid(case_id);

    try {

      materialService.send_Material(
          user_ids, material_id, case_id, casee.getSummary(), user.getUsername(), operation_id);
      resultData.setMessage("发送材料成功");
      resultData.setStatus(Constant.SEND_SUCCESS);
    } catch (Exception e) {
      resultData.setMessage("发送材料失败，请重试");
      resultData.setStatus(Constant.SEND_ERROR);
    } finally {

      return resultData;
    }
  }

  // 发表评论
  @PostMapping(value = "/{id}/comment")
  @ResponseBody
  public ResultData<Map<String, Object>> comment(
      @PathVariable("id") Long material_id, Comment comment, HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Long user_id = (Long) request.getAttribute("user_id");

    comment.setUser_id(user_id);
    comment.setMaterial_id(material_id);
    comment.setCreate_time(new Date());
    try {
      materialService.insert_Comment(comment);
      resultData.setMessage("发表批注成功");
      resultData.setStatus(Constant.PIZHU_SUCCESS);
    } catch (Exception e) {
      resultData.setMessage("发表批注失败，请重试");
      resultData.setStatus(Constant.PIZHU_ERROR);
    } finally {
      return resultData;
    }
  }

  // 材料审批
  @PostMapping("/{id}/approve")
  @ResponseBody
  public ResultData<Map<String, Object>> approve(
      @PathVariable("id") Long material_id, String note, int flag, HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Long user_id = (Long) request.getAttribute("user_id");

    Approve approve = new Approve();
    approve.setUser_id(user_id);
    approve.setMaterial_id(material_id);
    approve.setNote(note);
    approve.setCreate_time(new Date());
    approve.setFlag(flag);

    try {
      materialService.approve(approve);
      resultData.setMessage("审批成功");
      resultData.setStatus(Constant.SHENHE_SUCCESS);
    } catch (Exception e) {
      resultData.setMessage("审批失败");
      resultData.setStatus(Constant.SHENHE_ERROR);
    } finally {
      return resultData;
    }
  }

  // 材料详情从通知过去
  @GetMapping(value = "/{id}/from_notice")
  @ResponseBody
  public ResultData<Map<String, Object>> getmaterial_from_notice(
      @PathVariable("id") Long material_id, String readtype, Long noticeId) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Material material = materialService.getMaterial(material_id);
    List<XQ_commentVO> xQ_commentVOs = materialService.getCommentXiangQing(material_id);
    List<XQ_approveVO> xQ_approveVOs = materialService.getApproveXiangQing(material_id);

    map.put("material", material);
    map.put("xQ_commentVOs", xQ_commentVOs);
    map.put("xQ_approveVOs", xQ_approveVOs);

    if (readtype.equals("notice")) this.noticeService.updateById(noticeId);

    if (material.getFile_type() == 0) map.put("ViewName", "detail_picture");
    else if (material.getFile_type() == 1) map.put("ViewName", "detail_document");

    resultData.setData(map);
    resultData.setStatus(Constant.SUCCESS_DATA);
    resultData.setMessage("正常响应数据");
    return resultData;
  };

  ///////////////////////////////////////////////////////
  // 材料输出
  @PostMapping(value = "/output")
  @ResponseBody
  public ResultData<Map<String, Object>> material_output(
      Long material_id, int type, Long operation_id, HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    try {
      materialService.material_Output(material_id, operation_id, type);
      resultData.setStatus(Constant.OUTPUT_SUCCESS);
      resultData.setMessage("输出成果成功");
    } catch (Exception e) {
      resultData.setStatus(Constant.OUTPUT_ERROR);
      resultData.setMessage("输出成果失败，请重试");
    } finally {
      return resultData;
    }
  }

  // 材料输出列表
  @GetMapping("/outputlist")
  @ResponseBody
  public ResultData<Map<String, Object>> getmaterial_outputlist(
      Long operation_id, HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Long user_id = (Long) request.getAttribute("user_id");
    List<Material> materials = materialService.getMaterials_Output(operation_id, user_id);

    map.put("materials", materials);
    resultData.setData(map);
    resultData.setStatus(Constant.SUCCESS_DATA);
    resultData.setMessage("正常响应数据");
    return resultData;
  }

  // 删除成果,只重设输出标记
  @PostMapping("/outputDelete")
  @ResponseBody
  public ResultData<Map<String, Object>> output_delete(@RequestBody List<Long> material_ids) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();

    try {
      materialService.output_Delete(material_ids);
      resultData.setStatus(Constant.OUTPUT_DELETE_SUCCESS);
      resultData.setMessage("成果删除成功");
    } catch (Exception e) {
      resultData.setStatus(Constant.OUTPUT_DELETE_ERROR);
      resultData.setMessage("删除失败，请重试");
    } finally {
      return resultData;
    }
  }
}
