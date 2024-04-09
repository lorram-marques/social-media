package com.lorram.social.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.social.dto.UserDTO;
import com.lorram.social.entities.User;
import com.lorram.social.repositories.UserRepository;
import com.lorram.social.services.exceptions.DatabaseException;
import com.lorram.social.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	public UserDTO findById(Long id) {
		Optional<User> user = repository.findById(id);
		User entity = user.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
	}
	
	public UserDTO update(UserDTO dto, Long id) {
		User entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
			} catch(DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity violation");
			}
		return new UserDTO(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);;
	}
	
	private void fromDto(UserDTO dto, User entity) {
		entity.setName(dto.getName());
	}
}
