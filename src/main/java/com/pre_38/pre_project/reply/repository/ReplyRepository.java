package com.pre_38.pre_project.reply.repository;

import com.pre_38.pre_project.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    public Optional<Reply> findByContent(String content);
    //외래키로 찾을때는 find + By + {기본키 엔티티(첫글자는 대문자로)} + {외래키 필드명(첫글자는 대문자로)}
    public Page<Reply> findByQuestion_QuestionId(long questionId, Pageable pageable);
}
