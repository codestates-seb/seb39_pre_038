package com.pre_38.pre_project.reply.controller;

import com.pre_38.pre_project.dto.MultiResponseDto;
import com.pre_38.pre_project.dto.SingleResponseDto;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.service.MemberService;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.question.service.QuestionService;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import com.pre_38.pre_project.reply.mapper.ReplyMapper;
import com.pre_38.pre_project.reply.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/questions")
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

    //요구사항 3.4
    @GetMapping("/{question-id}/replies")
    public ResponseEntity getReplies(@PathVariable("question-id") @Positive long questionId,
                                     @RequestParam @Positive int page, @RequestParam @Positive int size){
        Page<Reply> pageReplies = replyService.findReplies(questionId,page-1,size);
        List<Reply> replies = pageReplies.getContent();
        List<ReplyDto.response> responses = mapper.repliesToReplyResponses(replies);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responses,pageReplies),HttpStatus.OK
        );
    }


    // 요구사항 3.2
    @PostMapping("/replies")
    public ResponseEntity postReply(@Valid @RequestBody ReplyDto.Post replyPost){
        Reply reply = mapper.replyPostToReply(replyPost);

        Member member = memberService.findMember(replyPost.getUsername());
        reply.setMember(member);

        Question question = questionService.findQuestion(replyPost.getQuestionId());
        reply.setQuestion(question);

        Reply posted = replyService.createReply(reply);
        ReplyDto.response response = mapper.replyToReplyResponse(posted);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),HttpStatus.CREATED
        );
    }

    // 요구사항 3.3
    @DeleteMapping("/{question-id}/{reply-id}")
    public ResponseEntity deleteReply(@PathVariable("question-id") @Positive long questionId,
                                      @PathVariable("reply-id") @Positive long replyId){

        replyService.deleteReply(replyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
