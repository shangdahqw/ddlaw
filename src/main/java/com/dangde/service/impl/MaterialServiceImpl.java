package com.dangde.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dangde.dao.ApproveDao;
import com.dangde.dao.CommentDao;
import com.dangde.dao.MaterialDao;
import com.dangde.dao.NoticeDao;
import com.dangde.dao.OperationDao;
import com.dangde.dao.UserDao;
import com.dangde.dao.cache.RedisDao;
import com.dangde.dao.third.User_Case_Dao;
import com.dangde.domain.Approve;
import com.dangde.domain.Comment;
import com.dangde.domain.Material;
import com.dangde.domain.Notice;
import com.dangde.domain.Operation;
import com.dangde.domain.model.PageBean;
import com.dangde.domain.model.QueryInfo;
import com.dangde.domain.model.XQ_approveVO;
import com.dangde.domain.model.XQ_commentVO;
import com.dangde.service.MaterialService;


@Service("materialService")
public class MaterialServiceImpl implements MaterialService {
    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());

	@Autowired
	MaterialDao materialDao;
	@Autowired
	CommentDao comment_dao;
	@Autowired
	NoticeDao noticeDao;
	
	@Autowired
	OperationDao operationDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ApproveDao approveDao;
	
    @Autowired //@Resource
	private User_Case_Dao user_Case_Dao;
	
    @Autowired
    private RedisDao redisDao;
    
    
	@Override
	public List<Object> getMaterials(Long operation_id, int material_type, Long userid,QueryInfo queryInfo) {
		// TODO Auto-generated method stub
		
		List<Material> materials=new ArrayList<Material>();
		materials=this.materialDao.getMaterials(operation_id, material_type,
				queryInfo.getStartindex(),queryInfo.getPagesize());
		long totalrecord=materialDao.getTotalrecord(operation_id, material_type);
		
				
		PageBean bean = new PageBean();
		bean.setCurrentpage(queryInfo.getCurrentpage());
		bean.setPagesize(queryInfo.getPagesize());
		bean.setTotalrecord(totalrecord);
		bean.setTotalpage();
		bean.setPagebar();			

		List<Object> materialsAndPagebean=new ArrayList<Object>();
		materialsAndPagebean.add(materials);
		materialsAndPagebean.add(bean);
		
		return materialsAndPagebean;
	}
    
	
	
	//上传材料
	@Transactional
	@Override
	public boolean insertMaterial(Material material,MultipartFile[] files,String dir,
			List<Long> userids_coopandcreate,Long userid_create,String casesummery,
			String username,Long caseId) {
		
		int file_type=material.getFile_type(); 
		String urls = "";
		try {

			if(files!=null&&files.length>0){
				for(int i=0;i<files.length;i++){
					MultipartFile file = files[i];
					String filename=file.getOriginalFilename();
					if(!file.isEmpty()){
						try {  
			                // 文件保存路径  
							
							String ext = filename.substring(filename.lastIndexOf("."));
							long l = System.nanoTime();
							File newFile = new File(dir,l + ext);		 
			                // 转存文件  
			                file.transferTo(newFile);  
			                String url = "";
			                if(file_type==0) //图片
			                	url = "upload/images/"+l +ext;
			                else if(file_type==1)
			                	url = "upload/documents/"+l +ext;
			                urls += url+" ";
			                 
			            } catch (Exception e) {  
			                e.printStackTrace();  
			            }  
					}
					
					if(file_type==1){//文档
						if(material.getFlag_output()==1) { //成果界面上传材料
							
							Long id=materialDao.material_Cancel_Output_getid(material.getOperation_id(), material.getType());
							
							if(id!=null){//之前有输出
								materialDao.material_Cancel_Output(id);
								//更新Redis缓存
						        Material mate = materialDao.getMaterial(id);
						        redisDao.putMaterial(mate);								
							}

						}
						
						String filename1 = filename.substring(0, filename.lastIndexOf('.'));
						urls = urls.substring(0,urls.length()-1);
						material.setUrl(urls);
						material.setUser_id(userid_create);
						material.setCreate_time(new Date());
						material.setTitle(filename1);
						materialDao.insertMaterial(material);
						urls = "";
					}
				}
				
			}
			if(file_type==0){//图片
				if(material.getFlag_output()==1) {
					Long id=materialDao.material_Cancel_Output_getid(material.getOperation_id(), material.getType());
					
					if(id!=null){//之前有输出
						materialDao.material_Cancel_Output(id);
						//更新Redis缓存
				        Material mate = materialDao.getMaterial(id);
				        redisDao.putMaterial(mate);
					}

				}
				if(urls.length()!=0){
					urls = urls.substring(0,urls.length()-1);
					material.setUrl(urls);
				}				
				
				material.setUser_id(userid_create);
				material.setCreate_time(new Date());
				materialDao.insertMaterial(material);
			}
			
			
			
			Long material_id=materialDao.getmaxid();				
			Operation operation = operationDao.getById(material.getOperation_id());
			
						
			Notice notice=new Notice();
			notice.setCasesummery(casesummery);
			notice.setCreate_time(new Date());
			notice.setMaterial_id(material_id);
			notice.setType(0);//0代表上传通知
			notice.setFlag(0);//0未查看 ，1已查看
			notice.setCase_id(caseId);
			notice.setOperation_id(material.getOperation_id());
			notice.setContent(username+"在"+casesummery+"的"+operation.getName()+"环节上传了"+material.getTitle()+"文件");

			for(Long userId :userids_coopandcreate){//给协作的所有人插入通知数据
				
				if(!userId.equals(userid_create)){ //自己不用插入通知
					notice.setUser_id(userId);
					this.noticeDao.insert_Notice(notice);
				}


			}
			
			return true;
		} catch (Exception e) {
			
			logger.error("saveMaterial inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("saveMaterial inner error :"+e.getMessage());
			
		}

	
		
		
	}

	@Override
	public Material getMaterial(Long material_id) {
		
		//No Redis 版本，获取材料时不用Redis
		 Material material = materialDao.getMaterial(material_id);
//		//优化点:缓存优化:超时的基础上维护一致性
//
//        Material material = redisDao.getMaterial(material_id);
//        
//        if (material == null) {
//            //2.访问数据库
//            material = materialDao.getMaterial(material_id);
//            redisDao.putMaterial(material);
//        }
        return  material;

	}
	
	//输出成果
	@Transactional
	@Override
	public boolean material_Output(Long material_id, Long operation_id, int type) {
		
		Long id;
		try {
			//根据operation_id, type找到之前输出成果的材料，取消输出标志位
			id=materialDao.material_Cancel_Output_getid(operation_id, type);
			
			if(id!=null)//之前有输出
				materialDao.material_Cancel_Output(id);
        
			//给新材料重新设置标志位
			materialDao.material_Output(material_id);
		
		} catch (Exception e) {
			logger.error("output material inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("output material inner error :"+e.getMessage());	
			
		}
		
		//更新Redis缓存
		if(id!=null){//之前有输出
	        Material material = materialDao.getMaterial(id);
	        redisDao.putMaterial(material);
		}
		
        Material material2 = materialDao.getMaterial(material_id);
        redisDao.putMaterial(material2);
        
		return true;

		
	}

	
	
	//发送材料
	@Transactional
	@Override
	public boolean send_Material(List<Long> user_ids,Long material_id,Long caseId,
			String casesummery,String username,Long operation_id) {
		
		try {
			user_Case_Dao.insertUser_Case(user_ids, caseId, 2); //开放好友case查看权限
			Operation operation = operationDao.getById(operation_id);

			
			Notice notice=new Notice();
			notice.setCasesummery(casesummery);
			notice.setCreate_time(new Date());
			notice.setMaterial_id(material_id);
			notice.setType(1);//1代表发送通知
			notice.setFlag(0);
			notice.setCase_id(caseId);
			notice.setOperation_id(operation_id);
			notice.setContent(username+"在"+casesummery+"的"+operation.getName()+"环节向您发送了审核通知");
			for(Long userId :user_ids){//给发送通知的所有人插入通知数据
				notice.setUser_id(userId);
				this.noticeDao.insert_Notice(notice);

			}

			return true;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			logger.error("send material inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("send material inner error :"+e.getMessage());	
		}
		
	}



	//对材料发表评论
	@Transactional
	@Override
	public boolean insert_Comment(Comment comment) {
		
		try {
			comment_dao.insert_Comment(comment);
			return true;
		} catch (Exception e) {
			
			logger.error("comment inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("comment inner error :"+e.getMessage());
		}
	
	}


	//审批操作
	@Transactional
	@Override
	public boolean approve(Approve approve) {
		try {
			approveDao.insertApprove(approve);
			approveDao.updateFlag(approve);
			
			return true;
		} catch (Exception e) {
			logger.error("approve inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("approve inner error :"+e.getMessage());
		}
		
	}


	//得到所有同意详情
	@Override
	public List<XQ_approveVO> getApproveXiangQing(Long material_id) {
		
		List<XQ_approveVO> xq_approveVOs = new ArrayList<XQ_approveVO>();
		List<Approve> approves = approveDao.getApprove(material_id);
		for(Approve approve:approves) {
			XQ_approveVO xq_approveVO = new XQ_approveVO();
			xq_approveVO.setUser_name(userDao.getUserName(approve.getUser_id()));

			xq_approveVO.setApprove(approve);

			xq_approveVOs.add(xq_approveVO);
		}
		return xq_approveVOs;
	}


	//得到所有评论详情
	@Override
	public List<XQ_commentVO> getCommentXiangQing(Long material_id) {
		
		List<XQ_commentVO> xq_commentVOs = new ArrayList<XQ_commentVO>();
		
		List<Comment> comments = comment_dao.getComment(material_id);
		for(Comment comment:comments) {
			XQ_commentVO xq_commentVO = new XQ_commentVO();
			xq_commentVO.setUser_name(userDao.getUserName(comment.getUser_id()));
			xq_commentVO.setComment(comment);
			xq_commentVOs.add(xq_commentVO);
		}
		return xq_commentVOs;
	}


	@Override
	public List<Material> getMaterials_Output(Long operation_id,Long userId) {
		
		List<Material> materials = this.materialDao.getMaterials_Output(operation_id);
		return materials;
	}


	//删除成果，更新材料flag_output为0
	@Transactional
	@Override
	public boolean output_Delete(List<Long> material_ids) {
		
		try {
			materialDao.material_delete_Output(material_ids);
		} catch (Exception e) {
			logger.error("删除成果 inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("删除成果 inner error :"+e.getMessage());
		}
		
		
		//更新Redis缓存
		for (Long id : material_ids) {
	        Material material = materialDao.getMaterial(id);
	        redisDao.putMaterial(material);
		}
		
		return true;
		
	}












}
