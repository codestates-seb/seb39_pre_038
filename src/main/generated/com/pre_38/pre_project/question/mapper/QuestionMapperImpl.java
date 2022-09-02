package com.pre_38.pre_project.question.mapper;

import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T14:14:22+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostToQuestion(QuestionDto.Post questionPost) {
        if ( questionPost == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPost.getTitle() );
        question.setContent( questionPost.getContent() );

        return question;
    }

    @Override
    public Question questionPatchToQuestion(QuestionDto.Patch questionPatch) {
        if ( questionPatch == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatch.getQuestionId() );
        question.setTitle( questionPatch.getTitle() );
        question.setContent( questionPatch.getContent() );

        return question;
    }

    @Override
    public QuestionDto.response questionToQuestionResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        List<ReplyDto.response> replies = null;
        long questionId = 0L;
        String title = null;
        String content = null;
        LocalDateTime date = null;
        long votes = 0L;
        Member member = null;

        replies = replyListToresponseList( question.getReplies() );
        if ( question.getQuestionId() != null ) {
            questionId = question.getQuestionId();
        }
        title = question.getTitle();
        content = question.getContent();
        date = question.getDate();
        votes = question.getVotes();
        member = question.getMember();

        QuestionDto.response response = new QuestionDto.response( questionId, title, content, date, votes, member, replies );

        return response;
    }

    @Override
    public List<QuestionDto.response> questionsToQuestionResponses(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.response> list = new ArrayList<QuestionDto.response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponse( question ) );
        }

        return list;
    }

    protected ReplyDto.response replyToresponse(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        long replyId = 0L;
        String content = null;
        LocalDateTime date = null;
        long votes = 0L;
        Member member = null;

        if ( reply.getReplyId() != null ) {
            replyId = reply.getReplyId();
        }
        content = reply.getContent();
        date = reply.getDate();
        votes = reply.getVotes();
        member = reply.getMember();

        ReplyDto.response response = new ReplyDto.response( replyId, content, date, votes, member );

        return response;
    }

    protected List<ReplyDto.response> replyListToresponseList(List<Reply> list) {
        if ( list == null ) {
            return null;
        }

        List<ReplyDto.response> list1 = new ArrayList<ReplyDto.response>( list.size() );
        for ( Reply reply : list ) {
            list1.add( replyToresponse( reply ) );
        }

        return list1;
    }
}
