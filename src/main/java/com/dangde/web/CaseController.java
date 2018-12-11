package com.dangde.web;

import com.dangde.domain.*;
import com.dangde.domain.model.Judges;
import com.dangde.domain.model.Layers;
import com.dangde.domain.model.Operations;
import com.dangde.domain.model.Trusteeoppositions;
import com.dangde.responseModle.ResultData;
import com.dangde.service.*;
import com.dangde.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/case") // url:模块/资源/{}/细分
public class CaseController {

  @Autowired private CaseService caseService;
  @Autowired private CourtService courtService;
  @Autowired private JudgeService judgeService;
  @Autowired private LayerService layerService;
  @Autowired private TrusteeoppositionService trusteeoppositionService;

  @GetMapping(value = "/cases")
  @ResponseBody
  public ResultData<Map<String, Object>> cases(HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Long user_id = (Long) request.getAttribute("user_id");
    if (user_id != null) {

      List<Case> caseList = this.caseService.findAll(user_id);
      map.put("caseList", caseList);
      resultData.setData(map);
      resultData.setStatus(Constant.SUCCESS_DATA);
      resultData.setMessage("正常响应数据");
      return resultData;
    }

    return resultData;
  }

  @PostMapping
  @ResponseBody
  public ResultData<Map<String, Object>> createcase(
      HttpServletRequest request,
      @RequestBody ArrayList<Long> userids,
      Case case1,
      Judges judges,
      Layers layers,
      Court court,
      Trusteeoppositions trusteeoppositions,
      Operations operations) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Long user_id = (Long) request.getAttribute("user_id");

    try {
      Long caseId =
          caseService.saveCase(
              case1, judges, layers, court, trusteeoppositions, operations, userids, user_id);

      map.put("caseId", caseId);
      resultData.setData(map);
      resultData.setStatus(Constant.CASE_ADD_SUCCESS);
      resultData.setMessage("新建case成功");

    } catch (Exception e) {
      // TODO Auto-generated catch block
      resultData.setStatus(Constant.CASE_ADD_ERROR);
      resultData.setMessage("新建case失败，服务器出错了");
    } finally {
      return resultData;
    }
  }

  @GetMapping(value = "/{caseId}/todolist")
  @ResponseBody
  public ResultData<Map<String, Object>> caseetodolist(
      @PathVariable("caseId") Long caseId, HttpServletRequest request) {

    if (caseId == null) return null; // caseId参数必须传递

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    List<Operation> operations = this.caseService.getOperationBycaseId(caseId);
    Case casee = this.caseService.findByid(caseId);
    Long user_id = (Long) request.getAttribute("user_id");

    if (user_id != null) {
      // case权限
      int privilege_Id = this.caseService.getprivilege_Id(user_id, caseId);

      map.put("casee", casee);
      map.put("operations", operations);
      map.put("privilege_Id", privilege_Id);

      resultData.setData(map);
      resultData.setStatus(Constant.SUCCESS_DATA);
      resultData.setMessage("正常响应数据");
      return resultData;
    }
    return resultData;
  }

  // 查询case详情
  @GetMapping(value = "/{caseId}")
  @ResponseBody
  public ResultData<Map<String, Object>> casee(@PathVariable("caseId") Long caseId) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    if (caseId != null) {

      Case casee = this.caseService.findByid(caseId);
      Court court = this.courtService.findByid(casee.getCourt_id());

      List<Layer> layers = new ArrayList<Layer>();
      layers = this.layerService.findByCaseId(caseId);

      List<Judge> judges = new ArrayList<Judge>();
      judges = this.judgeService.findByCaseId(caseId);

      List<Trusteeopposition> trusteeoppositions = new ArrayList<Trusteeopposition>();
      trusteeoppositions = this.trusteeoppositionService.findByCaseId(caseId);

      map.put("court", court);
      map.put("layers", layers);
      map.put("judges", judges);
      map.put("Trusteeoppositions", trusteeoppositions);

      resultData.setData(map);
      resultData.setStatus(Constant.SUCCESS_DATA);
      resultData.setMessage("正常响应数据");
      return resultData;
    }

    return resultData;
  }
}
