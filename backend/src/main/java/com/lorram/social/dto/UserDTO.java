package com.lorram.social.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lorram.social.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	
	List<PostDTO> posts = new ArrayList<>();
	
	Set<LikeLogDTO> likes = new HashSet<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, List<PostDTO> posts, Set<LikeLogDTO> likes) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.posts = posts;
		this.likes = likes;
	}

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		posts = user.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		likes = user.getLikes().stream().map(x -> new LikeLogDTO(x)).collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public Set<LikeLogDTO> getLikes() {
		return likes;
	}

	public void setLikes(Set<LikeLogDTO> likes) {
		this.likes = likes;
	}
}
