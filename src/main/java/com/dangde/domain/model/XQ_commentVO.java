package com.dangde.domain.model;

import com.dangde.domain.Comment;

public class XQ_commentVO {

	private String user_name;
	private Comment comment;
	
	
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


}
