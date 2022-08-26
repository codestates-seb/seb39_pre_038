package com.pre_38.pre_project.reply.controller;

import com.pre_38.pre_project.dto.SingleResponseDto;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import com.pre_38.pre_project.reply.mapper.ReplyMapper;
import com.pre_38.pre_project.reply.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@Slf4j
@RequestMapping("/replys")
public class ReplyController {
    private final ReplyService replyService;
    private final ReplyMapper mapper;
    private final MemberService memberService;
    private final QuestionService questionService;

    public ReplyController(ReplyService replyService,
                           ReplyMapper mapper,
                           MemberService memberService,
                           QuestionService questionService) {
        this.replyService = replyService;
        this.mapper = mapper;
        this.memberService = memberService;
        this.questionService = questionService;
    }

    //요구사항 3.1
    @GetMapping("/{reply-id}")
    public ResponseEntity getReply(
            @PathVariable("reply-id") @Positive long replyId) {
        Reply reply = replyService.findReply(replyId);  // 물어보기
        ReplyDto.response response = mapper.replyToReplyResponse(reply);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response)
                , HttpStatus.OK);
    }


    // 요구사항 3.2
    @PostMapping("/ask")
    public ResponseEntity postQuestion(@Valid @RequestBody Reply.Post replyPost){
        Reply reply = mapper.ReplyPostToQuestion(replyPost);
        Member member = memberService.findMember(ReplyPost.getName());
        Reply.setMember(member);

        Question question = QuestionService.findQuestions(ReplyPost.getName());
        Reply.setQuestion(question);

        Reply posted = replyService.createReply(reply);
        ReplyDto.response response = mapper.replyToReplyResponse(posted);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),HttpStatus.CREATED
        );
    }

    // 요구사항 3.3
    @DeleteMapping("/{reply-id}")
    public ResponseEntity deleteReply(@PathVariable("reply-id") @Positive long replyId){
        replyService.deleteReply(replyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //  요구사항 3.4 구현 예정


}
