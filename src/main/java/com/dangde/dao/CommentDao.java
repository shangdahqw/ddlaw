package com.dangde.dao;

import com.dangde.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {

  public boolean insertComment(Comment comment);

  public List<Comment> getComment(@Param("material_id") Long materialId);
}
