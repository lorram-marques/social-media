package com.lorram.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.social.entities.LikeLog;

@Repository
public interface LikeLogRepository extends JpaRepository<LikeLog, Long>{

}
