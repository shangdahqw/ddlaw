package com.dangde.service;



import java.util.List;
import com.dangde.domain.Notice;





public interface NoticeService {


	public List<Notice> findAll(Long userId);
	public List<Notice> findAllshenhe(Long userId);

	public void updateById(Long id);


}
