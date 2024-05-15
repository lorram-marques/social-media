package com.lorram.social.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorram.social.dto.LikeLogDTO;
import com.lorram.social.dto.PostDTO;
import com.lorram.social.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService service;
	
	@GetMapping 
	public ResponseEntity<Page<PostDTO>> findAll(Pageable pageable) {
		Page<PostDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable Long id) {
		PostDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/likes")
	public ResponseEntity<Page<LikeLogDTO>> findLikesById(@PathVariable Long id, Pageable pageable) {
		Page<LikeLogDTO> dto = service.findLikesById(id, pageable);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PostDTO> update(@RequestBody PostDTO dto, @PathVariable Long id) {
		PostDTO newDto = service.update(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@PostMapping
	public ResponseEntity<PostDTO> insert(@RequestBody PostDTO dto) {
		PostDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PostMapping(value = "/{id}/likepost")
	public ResponseEntity<LikeLogDTO> likePost(@RequestBody LikeLogDTO dto, @PathVariable Long id) {
		LikeLogDTO newDto = service.likePost(dto, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
