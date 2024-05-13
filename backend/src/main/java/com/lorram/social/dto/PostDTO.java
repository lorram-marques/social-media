package com.lorram.social.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lorram.social.entities.Post;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private LocalDateTime date;
	private Long userId;
	
	List<CommentDTO> comments = new ArrayList<>();
	
	public PostDTO() {
	}
	
	public PostDTO(Long id, String text, LocalDateTime date, Long userId) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.userId = userId;
	}

	public PostDTO(Post post) {
		id = post.getId();
		text = post.getText();
		date = post.getDate();
		userId = post.getUser().getId();
		comments = post.getComments().stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
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

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
}
