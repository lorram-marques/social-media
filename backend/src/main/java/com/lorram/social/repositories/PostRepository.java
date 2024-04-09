package com.lorram.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.social.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
