package com.lorram.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lorram.social.entities.LikeLog;

@Repository
public interface LikeLogRepository extends JpaRepository<LikeLog, Long> {

	@Query("SELECT u.id FROM User u JOIN u.likes l JOIN l.post p WHERE u.id = :userId AND p.id = :postId")
	public Long findByUserId(@Param("userId") Long userId, @Param("postId") Long postId);

	@Query("SELECT l.id FROM User u JOIN u.likes l JOIN l.post p WHERE u.id = :userId AND p.id = :postId")
	public Long findLikeId(@Param("userId") Long userId, @Param("postId") Long postId);

}
