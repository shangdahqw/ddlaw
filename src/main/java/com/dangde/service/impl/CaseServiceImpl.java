package com.dangde.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dangde.dao.CaseDao;
import com.dangde.dao.CourtDao;
import com.dangde.dao.JudgeDao;
import com.dangde.dao.LayerDao;
import com.dangde.dao.OperationDao;
import com.dangde.dao.TrusteeoppositionDao;
import com.dangde.dao.third.Case_Judge_Dao;
import com.dangde.dao.third.Case_Layer_Dao;
import com.dangde.dao.third.Case_Trusteeopposition_Dao;
import com.dangde.dao.third.User_Case_Dao;
import com.dangde.domain.Case;
import com.dangde.domain.Court;
import com.dangde.domain.Judge;
import com.dangde.domain.Layer;
import com.dangde.domain.Operation;
import com.dangde.domain.Trusteeopposition;
import com.dangde.domain.model.Judges;
import com.dangde.domain.model.Layers;
import com.dangde.domain.model.Operations;
import com.dangde.domain.model.Trusteeoppositions;
import com.dangde.service.CaseService;


@Service("caseService")
public class CaseServiceImpl implements CaseService{

    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());
	//注入Service依赖
    @Autowired //@Resource
	private CaseDao caseDao;
    
    @Autowired //@Resource
	private JudgeDao judgeDao;
    
    @Autowired //@Resource
	private CourtDao courtDao;
    
    @Autowired //@Resource
	private LayerDao layerDao;
    
    @Autowired //@Resource
	private OperationDao  operationDao;
    
    @Autowired //@Resource
	private TrusteeoppositionDao trusteeoppositionDao;
	
    @Autowired //@Resource
	private Case_Judge_Dao case_Judge_Dao;
    
    @Autowired //@Resource
	private Case_Layer_Dao case_Layer_Dao;
    
    @Autowired //@Resource
	private Case_Trusteeopposition_Dao  case_Trusteeopposition_Dao;
    
    
    @Autowired //@Resource
	private User_Case_Dao user_Case_Dao;
    

    @Transactional
	@Override
	public Long saveCase(Case case1,Judges judges,Layers layers,Court court,
			Trusteeoppositions trusteeoppositions,Operations operations,
			ArrayList<Long> userids_coop,Long userid){
    	
    	
    	try {
			courtDao.insertCourt(court);
			
			case1.setCreattime(new Date());
			case1.setCourt_id(courtDao.getmaxkey());
			caseDao.insertCase(case1);
			
			Long case_id=caseDao.getmaxkey();
			
			
			List<Judge> judgeList = judges.getJudges();
			for (Judge judge:judgeList){
				judgeDao.insertJudge(judge);
				Long judge_id=judgeDao.getmaxkey();
				case_Judge_Dao.insertCase_Judge(case_id, judge_id);
			}
			
			List<Layer> layerList = layers.getLayers();
			for (Layer layer:layerList){
				layerDao.insertLayer(layer);
				Long layer_id=layerDao.getmaxkey();
				case_Layer_Dao.insertCase_Layer(case_id, layer_id);
			}
			
			List<Trusteeopposition> trusteeoppositionList = trusteeoppositions.getTrusteeoppositions();
			for (Trusteeopposition trusteeopposition:trusteeoppositionList){
				trusteeoppositionDao.insertTrusteeopposition(trusteeopposition);
				Long trusteeopposition_id=trusteeoppositionDao.getmaxkey();
				case_Trusteeopposition_Dao.insertCase_Trusteeopposition(case_id, trusteeopposition_id);
			}
			
			
			List<Operation> operationList = operations.getOperations();
			for (Operation operation:operationList){
				operation.setCase_id(case_id);
				operationDao.insertOperation(operation);

			}
		
			ArrayList<Long> userids_create=new ArrayList<Long>();
			userids_create.add(userid);		
			user_Case_Dao.insertUser_Case(userids_create, case_id, 0);
			
			if(userids_coop!=null){
				user_Case_Dao.insertUser_Case(userids_coop, case_id, 1);
			}
			
			return case_id;

			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("saveCase inner error :"+e.getMessage(),e);
            //所以编译期异常转化为运行期异常
			throw new RuntimeException("saveCase inner error :"+e.getMessage());
		}
			
    	
    }

	@Override
	public void updateCase(Case Case) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Operation> getOperationBycaseId(Long caseId) {
		// TODO Auto-generated method stub
		return this.operationDao.getOperationBycaseId(caseId);
	}

	
	
	@Override
	public List<Long> selectCoopidsAndCreateids(Long case_id) {
		// TODO Auto-generated method stub
		return user_Case_Dao.selectCoopidsAndCreateids(case_id);
	}

	@Override
	public List<Case> findAll(Long userId) {
		// TODO Auto-generated method stub
		
		
		return this.caseDao.findAll(userId);
	}

	@Override
	public Case findByid(Long caseId) {
		// TODO Auto-generated method stub
		return this.caseDao.findByid(caseId);
	}

	
	//根据userId和caseId得到用户对此case的权限
	@Override
	public int getprivilege_Id(Long userId, Long caseId) {
		
		return this.user_Case_Dao.getprivilege_Id(userId, caseId);
		
	}

	@Override
	public List<Case> findWork(Long userId) {
		// TODO Auto-generated method stub
		return this.caseDao.findWork(userId,new Date());
	}


	
	
}
