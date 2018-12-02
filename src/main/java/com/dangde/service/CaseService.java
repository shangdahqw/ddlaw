package com.dangde.service;


import java.util.ArrayList;
import java.util.List;

import com.dangde.domain.Case;
import com.dangde.domain.Court;
import com.dangde.domain.Operation;
import com.dangde.domain.model.Judges;
import com.dangde.domain.model.Layers;
import com.dangde.domain.model.Operations;
import com.dangde.domain.model.Trusteeoppositions;




public interface CaseService {

	
	public Long saveCase(Case case1,Judges judges,Layers layers,Court court,Trusteeoppositions trusteeoppositions,Operations operations,
			ArrayList<Long> ids,Long userid);


	public void updateCase(Case Case);
	
	public List<Operation>  getOperationBycaseId(Long caseId);

	public List<Long> selectCoopidsAndCreateids(Long case_id);

	public List<Case> findAll(Long userId);
	public List<Case> findWork(Long userId);

	public Case findByid(Long caseId);

	public int getprivilege_Id(Long userId,Long caseId);


}
