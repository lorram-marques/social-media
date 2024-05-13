package com.lorram.social.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.lorram.social.entities.Comment;

public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private LocalDateTime date;
	private Long userId;
	private Long postId;
	
	public CommentDTO() {
	}

	public CommentDTO(Long id, String text, LocalDateTime date, Long userId, Long postId) {
		this.id = id;
		this.text = text;
		this.date = date;
		this.userId = userId;
		this.postId = postId;
	}
	
	public CommentDTO(Comment comment) {
		id = comment.getId();
		text = comment.getText();
		date = comment.getDate();
		userId = comment.getUser().getId();
		postId = comment.getPost().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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
