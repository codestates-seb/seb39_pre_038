package com.pre_38.pre_project.reply.service;

import com.pre_38.pre_project.exception.BusinessLogicException;
import com.pre_38.pre_project.exception.ExceptionCode;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.reply.entity.Reply;
import com.pre_38.pre_project.reply.repository.ReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository){
        this.replyRepository = replyRepository;
    }

    // 요구사항 3.1 + 3.4
    public Page<Reply> findReplies(long questionId,int page, int size){
        return replyRepository.findByQuestion_QuestionId(questionId, PageRequest.of(page,size, Sort.by("replyId").descending()));
    }

    // 요구사항 3.2
    public Reply createReply(Reply reply){
//        이미 댓글이 존재하는지 확인(선택)
//        verifyExistsContent(reply.getContent());

        return replyRepository.save(reply);
    }

    // 요구사항 3.3
    public void deleteReply(long replyId){
        Reply findReply = findVerifiedReply(replyId);

        replyRepository.delete(findReply);
    }

    //댓글의 중복을 확인하는 메서드
    private void verifyExistsContent(String content){
        Optional<Reply> reply = replyRepository.findByContent(content);
        if(reply.isPresent()){
            throw new BusinessLogicException(ExceptionCode.REPLY_EXISTS);
        }
    }

    public Reply findVerifiedReply(long replyId){
<<<<<<< HEAD
        Optional<Reply> optionalReply = replyRepository.findById(replyId);
=======
        //Id 값으로 검색
        Optional<Reply> optionalReply = replyRepository.findById(replyId);
        //존재 여부 확인
>>>>>>> main
        Reply findReply =
                optionalReply.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.REPLY_NOT_FOUND));

        return findReply;
    }

}
