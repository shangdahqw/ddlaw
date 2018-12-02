package com.dangde.dao.third;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface User_Case_Dao {

		public List<Long> selectCoopidsAndCreateids(@Param("case_id")Long case_id);
		public void insertUser_Case(@Param("user_ids")List<Long> userids, @Param("case_id")Long case_id,@Param("privilege_id")int privilege_id);

		public int getprivilege_Id(@Param("userId")Long userId,@Param("caseId")Long caseId);
}
