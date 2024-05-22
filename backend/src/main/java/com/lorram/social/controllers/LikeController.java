package com.lorram.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorram.social.dto.LikeLogDTO;
import com.lorram.social.services.LikeLogService;

@RestController
@RequestMapping(value = "/likelogs")
public class LikeController {

	@Autowired
	private LikeLogService service;
	
	@GetMapping 
	public ResponseEntity<Page<LikeLogDTO>> findAll(Pageable pageable) {
		Page<LikeLogDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LikeLogDTO> findById(@PathVariable Long id) {
		LikeLogDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
