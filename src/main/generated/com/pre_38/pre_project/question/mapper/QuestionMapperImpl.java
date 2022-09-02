package com.pre_38.pre_project.question.mapper;

<<<<<<< HEAD
import com.pre_38.pre_project.member.entity.Member;
=======
import com.pre_38.pre_project.member.dto.MemberDtoresponse;
import com.pre_38.pre_project.member.mapper.MemberMapper;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-09-02T14:14:22+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
=======
    date = "2022-09-02T13:56:18+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

<<<<<<< HEAD
=======
    @Autowired
    private MemberMapper memberMapper;

>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
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
<<<<<<< HEAD
        long votes = 0L;
        Member member = null;
=======
        MemberDtoresponse member = null;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486

        replies = replyListToresponseList( question.getReplies() );
        if ( question.getQuestionId() != null ) {
            questionId = question.getQuestionId();
        }
        title = question.getTitle();
        content = question.getContent();
        date = question.getDate();
<<<<<<< HEAD
        votes = question.getVotes();
        member = question.getMember();

        QuestionDto.response response = new QuestionDto.response( questionId, title, content, date, votes, member, replies );
=======
        member = memberMapper.MemberToMemberDtoresponse( question.getMember() );

        QuestionDto.response response = new QuestionDto.response( questionId, title, content, date, member, replies );
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486

        return response;
    }

<<<<<<< HEAD
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

=======
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
    protected ReplyDto.response replyToresponse(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        long replyId = 0L;
        String content = null;
        LocalDateTime date = null;
<<<<<<< HEAD
        long votes = 0L;
        Member member = null;
=======
        MemberDtoresponse member = null;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486

        if ( reply.getReplyId() != null ) {
            replyId = reply.getReplyId();
        }
        content = reply.getContent();
        date = reply.getDate();
<<<<<<< HEAD
        votes = reply.getVotes();
        member = reply.getMember();

        ReplyDto.response response = new ReplyDto.response( replyId, content, date, votes, member );
=======
        member = memberMapper.MemberToMemberDtoresponse( reply.getMember() );

        ReplyDto.response response = new ReplyDto.response( replyId, content, date, member );
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486

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
