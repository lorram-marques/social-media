package com.lorram.social.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.social.dto.LikeLogDTO;
import com.lorram.social.entities.LikeLog;
import com.lorram.social.repositories.LikeLogRepository;
import com.lorram.social.repositories.PostRepository;
import com.lorram.social.repositories.UserRepository;
import com.lorram.social.services.exceptions.DatabaseException;
import com.lorram.social.services.exceptions.ResourceNotFoundException;

@Service
public class LikeLogService {

	@Autowired
	private LikeLogRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	public Page<LikeLogDTO> findAll(Pageable pageable) {
		Page<LikeLog> list = repository.findAll(pageable);
		return list.map(x -> new LikeLogDTO(x));
	}

	public LikeLogDTO findById(Long id) {
		Optional<LikeLog> likeLog = repository.findById(id);
		LikeLog entity = likeLog.orElseThrow(() -> new ResourceNotFoundException(id));
		return new LikeLogDTO(entity);
	}

	public LikeLogDTO insert(LikeLogDTO dto) {
		LikeLog entity = new LikeLog();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		return new LikeLogDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void fromDto(LikeLogDTO dto, LikeLog entity) {
		entity.setUser(userRepository.getReferenceById(dto.getUserId()));
		entity.setPost(postRepository.getReferenceById(dto.getPostId()));
	}
}
