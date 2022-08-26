package com.pre_38.pre_project.reply.service;

import com.pre_38.pre_project.reply.entity.Reply;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReplyService {

    // 요구사항 3.1
    public List<Reply> findReply(){
        return null;
    }

    // 요구사항 3.2
    public Reply createReply(Reply reply){
        return null;
    }

    // 요구사항 3.3
    public void deleteReply(long replyId){

    }

}
