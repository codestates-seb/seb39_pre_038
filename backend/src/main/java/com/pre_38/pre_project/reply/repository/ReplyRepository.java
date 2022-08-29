package com.pre_38.pre_project.reply.repository;

import com.pre_38.pre_project.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    Optional<Reply> findByContent(String content);
}
