package com.dangde.utils;


import java.util.UUID;

public class Constant {
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
	public static final String JWT_SECERT = "7786df7fc3a34e26a61c034d5ec8245d";			//密匙,自己定义
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    

	/**
	 * 基本code
	 */
	public static final int REGISTER_SUCCESS = 1000;//注册成功		
	public static final int REGISTER_FAIL = 1001;//注册失败		
	public static final int USER_EXIT = 1002;//用户已经存在
	public static final int LOGIN_SUCCESS=1003;//登入成功
	public static final int LOGIN_FAIL=1004;//登入失败，账号或密码错误
	public static final int USER_INFO_UPDATE_SUCCESS=1005;//修改信息成功
	public static final int USER_INFO_UPDATE_ERROR=1006;//修改信息失败，服务器出错了
	public static final int USER_PASSWORD_UPDATE_FAIL=1007;//修改密码失败，原密码输入错误
	public static final int USER_PASSWORD_UPDATE_SUCCESS=1008;//修改密码成功
	public static final int USER_PASSWORD_UPDATE_ERROR=1009;//修改密码失败，服务器出错了
	public static final int USER_EMAIL_UPDATE_SUCCESS=1010;//修改邮箱成功
	public static final int USER_EMAIL_UPDATE_ERROR=1011;//修改邮箱失败，服务器出错了
	public static final int CASE_ADD_SUCCESS=1013;//新建case成功
	public static final int CASE_ADD_ERROR=1012;//新建case失败，服务器出错了
	public static final int UPLOAD_SUCCESS=1014;//上传成功
	public static final int UPLOAD_ERROR=1015;//上传失败，服务器出错了
	public static final int SEND_SUCCESS=1016;//发送材料成功
	public static final int SEND_ERROR=1017;//发送材料失败，请重试
	public static final int PIZHU_SUCCESS=1018;//发表批注成功
	public static final int PIZHU_ERROR=1019;//发表批注失败，请重试
	public static final int SHENHE_SUCCESS=1020;//审批成功
	public static final int SHENHE_ERROR=1021;//审批失败
	public static final int OUTPUT_SUCCESS=1022;//输出成果成功
	public static final int OUTPUT_ERROR=1023;//输出成果失败，请重试
	public static final int OUTPUT_DELETE_SUCCESS=1024;//成果删除成功
	public static final int OUTPUT_DELETE_ERROR=1025;//删除失败，请重试
	public static final int LOGOUT_SUCCESS=1026;//登出成功
	public static final int LOGOUT_FAIL=1027;//登出失败

	
	
	public static final int SUCCESS_DATA = 2000;				//正常响应数据
	public static final int INNER_ERROR = 2001;				//内部错误
	public static final int NOLOGIN = 2002;				//未登陆状态
	public static final int TOKEN_EXPIRE = 2003;			//Token过期
	public static final int TOKEN_VALITADE_FAIL = 2004;			//Token验证不通过



	
	
	
	
	
	
    
}
