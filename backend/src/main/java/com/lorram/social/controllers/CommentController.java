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

import com.lorram.social.dto.CommentDTO;
import com.lorram.social.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	private CommentService service;
	
	@GetMapping 
	public ResponseEntity<Page<CommentDTO>> findAll(Pageable pageable) {
		Page<CommentDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> findById(@PathVariable Long id) {
		CommentDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CommentDTO> update(@RequestBody CommentDTO dto, @PathVariable Long id) {
		CommentDTO newDto = service.update(dto, id);
		return ResponseEntity.ok().body(newDto);
	}
	
	@PostMapping
	public ResponseEntity<CommentDTO> insert(@RequestBody CommentDTO dto) {
		CommentDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
