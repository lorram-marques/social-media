package com.lorram.social.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.social.dto.CommentDTO;
import com.lorram.social.entities.Comment;
import com.lorram.social.repositories.CommentRepository;
import com.lorram.social.repositories.PostRepository;
import com.lorram.social.repositories.UserRepository;
import com.lorram.social.services.exceptions.DatabaseException;
import com.lorram.social.services.exceptions.ResourceNotFoundException;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	public Page<CommentDTO> findAll(Pageable pageable) {
		Page<Comment> list = repository.findAll(pageable);
		return list.map(x -> new CommentDTO(x));
	}

	public CommentDTO findById(Long id) {
		Optional<Comment> comment = repository.findById(id);
		Comment entity = comment.orElseThrow(() -> new ResourceNotFoundException(id));
		return new CommentDTO(entity);
	}

	public CommentDTO update(CommentDTO dto, Long id) {
		Comment entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new CommentDTO(entity);
	}

	public CommentDTO insert(CommentDTO dto) {
		Comment entity = new Comment();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		return new CommentDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void fromDto(CommentDTO dto, Comment entity) {
		entity.setText(dto.getText());
		entity.setDate(dto.getDate());
		entity.setPost(postRepository.getReferenceById(dto.getPostId()));
		entity.setUser(userRepository.getReferenceById(dto.getUserId()));
	}
}
