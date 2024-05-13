package com.lorram.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.social.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
