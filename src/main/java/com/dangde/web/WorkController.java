package com.dangde.web;

import com.dangde.domain.Case;
import com.dangde.domain.Notice;
import com.dangde.responseModle.ResultData;
import com.dangde.service.CaseService;
import com.dangde.service.NoticeService;
import com.dangde.service.UserService;
import com.dangde.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/user")
public class WorkController {

  @Autowired private NoticeService noticeService;

  @Autowired private CaseService caseService;

  @Autowired private UserService userService;

  @GetMapping(value = "/{id}/works")
  @ResponseBody
  public ResultData<Map<String, Object>> works(HttpServletRequest request) {

    ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();

    Long user_id = (Long) request.getAttribute("user_id");
    if (user_id != null) {

      List<Notice> noticeList = this.noticeService.findAll(user_id);
      List<Case> caseList = this.caseService.findWork(user_id);
      map.put("noticeList", noticeList);
      map.put("caseList", caseList);

      resultData.setData(map);
      resultData.setStatus(Constant.SUCCESS_DATA);
      resultData.setMessage("正常响应数据");

      return resultData;
    }

    resultData.setStatus(Constant.INNER_ERROR);
    resultData.setMessage("服务器出错");
    return resultData;
  }
}
