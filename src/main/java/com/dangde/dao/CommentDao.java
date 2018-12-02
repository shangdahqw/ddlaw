package com.dangde.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dangde.domain.Comment;

public interface CommentDao {
	
	public boolean insert_Comment(Comment comment);
	
	public List<Comment> getComment(@Param("material_id")Long material_id);
}
