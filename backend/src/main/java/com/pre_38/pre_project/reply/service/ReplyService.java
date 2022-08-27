package com.pre_38.pre_project.reply.service;

import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReplyService {

    // 요구사항 3.1 + 3.4
    public Page<Reply> findReplies(Question question,int page, int size){
        //PageImpl<>() 사용
        return null;
    }

    // 요구사항 3.2
    public Reply createReply(Reply reply){
        return null;
    }

    // 요구사항 3.3
    public void deleteReply(long replyId, long questionId){
        //Questoin에서 Replies 관계 반드시  제거  할것
    }



}
