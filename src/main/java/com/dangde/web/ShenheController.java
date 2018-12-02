package com.dangde.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dangde.domain.Notice;
import com.dangde.service.NoticeService;
import com.dangde.utils.Constant;
import com.dangde.responseModle.ResultData;



@Component
@RequestMapping("/user")
public class ShenheController {
	
	
	@Autowired
	private NoticeService noticeService;
		
	@GetMapping(value="/{id}/shenhes")
    @ResponseBody
	public ResultData<Map<String,Object>> works(HttpServletRequest request){
		
		ResultData<Map<String,Object>> resultData =new ResultData<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();

		Long user_id=(Long) request.getAttribute("user_id");
		if(user_id!=null){
			
			List<Notice> noticeList= this.noticeService.findAllshenhe(user_id);//找到所有未查看的审核通知	
			map.put("noticeList", noticeList);
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