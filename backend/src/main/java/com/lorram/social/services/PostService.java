package com.lorram.social.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.social.dto.PostDTO;
import com.lorram.social.entities.Post;
import com.lorram.social.repositories.PostRepository;
import com.lorram.social.repositories.UserRepository;
import com.lorram.social.services.exceptions.DatabaseException;
import com.lorram.social.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	@Autowired 
	private UserRepository userRepository;
	
	public Page<PostDTO> findAll(Pageable pageable) {
		Page<Post> list = repository.findAll(pageable);
		return list.map(x -> new PostDTO(x));
	}
	
	public PostDTO findById(Long id) {
		Optional<Post> post = repository.findById(id);
		Post entity = post.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PostDTO(entity);
	}
	
	public PostDTO update(PostDTO dto, Long id) {
		Post entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new PostDTO(entity);
	}
	
	public PostDTO insert(PostDTO dto) {
		Post entity = new Post();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
			} catch(DataIntegrityViolationException e) {
				throw new DatabaseException("Integrity violation");
			}
		return new PostDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void fromDto(PostDTO dto, Post entity) {
		entity.setText(dto.getText());
		entity.setDate(dto.getDate());
		entity.setUser(userRepository.getReferenceById(dto.getUserId()));
	}
}
