package com.lorram.social.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.lorram.social.entities.Post;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}

	private String text;
	private LocalDateTime date;
	
	public PostDTO() {
	}
	
	public PostDTO(String text, LocalDateTime date) {
		this.text = text;
		this.date = date;
	}
	
	public PostDTO(Post post) {
		text = post.getText();
		date = post.getDate();
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

	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostDTO other = (PostDTO) obj;
		return Objects.equals(date, other.date) && Objects.equals(text, other.text);
	}
}
