package com.lorram.social.dto;

import java.io.Serializable;

import com.lorram.social.entities.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
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
}
