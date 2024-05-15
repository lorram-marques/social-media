package com.lorram.social.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Like counting for posts only
@Entity
@Table(name = "tb_likelog")
public class LikeLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinTable(name = "tb_like_user",
		joinColumns = @JoinColumn(name = "like_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	@ManyToOne
	@JoinTable(name = "tb_like_post",
		joinColumns = @JoinColumn(name = "like_id"),
		inverseJoinColumns = @JoinColumn(name = "post_id"))
	private Post post;
	
	public LikeLog() {
	}

	public LikeLog(Long id, User user, Post post) {
		this.id = id;
		this.user = user;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeLog other = (LikeLog) obj;
		return Objects.equals(id, other.id);
	}
}
