package com.lorram.social.dto;

import java.io.Serializable;

import com.lorram.social.entities.LikeLog;

public class LikeLogDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Long postId;
	
	public LikeLogDTO() {
	}

	public LikeLogDTO(Long id, Long userId, Long postId) {
		this.id = id;
		this.userId = userId;
		this.postId = postId;
	}

	public LikeLogDTO(LikeLog likeLog) {
		id = likeLog.getId();
		userId = likeLog.getUser().getId();
		postId = likeLog.getPost().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}	
}
