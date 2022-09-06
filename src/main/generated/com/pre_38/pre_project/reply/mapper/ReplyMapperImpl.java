package com.pre_38.pre_project.reply.mapper;

<<<<<<< HEAD
=======
import com.pre_38.pre_project.member.dto.MemberDtoresponse;
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
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
public class ReplyMapperImpl implements ReplyMapper {

    @Override
    public Reply replyPostToReply(ReplyDto.Post replyPost) {
        if ( replyPost == null ) {
            return null;
        }

        Reply reply = new Reply();

        reply.setContent( replyPost.getContent() );

        return reply;
    }

    @Override
    public Reply replyPatchToReply(ReplyDto.Patch replyPatch) {
        if ( replyPatch == null ) {
            return null;
        }

        Reply reply = new Reply();

        reply.setReplyId( replyPatch.getReplyId() );
        reply.setContent( replyPatch.getContent() );

        return reply;
    }

    @Override
    public ReplyDto.response replyToReplyResponse(Reply reply) {
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
        member = memberToMemberDtoresponse( reply.getMember() );

        ReplyDto.response response = new ReplyDto.response( replyId, content, date, member );
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486

        return response;
    }

    @Override
    public List<ReplyDto.response> repliesToReplyResponses(List<Reply> replies) {
        if ( replies == null ) {
            return null;
        }

        List<ReplyDto.response> list = new ArrayList<ReplyDto.response>( replies.size() );
        for ( Reply reply : replies ) {
            list.add( replyToReplyResponse( reply ) );
        }

        return list;
    }
<<<<<<< HEAD
=======

    protected MemberDtoresponse memberToMemberDtoresponse(Member member) {
        if ( member == null ) {
            return null;
        }

        String username = null;
        String avatar = null;

        username = member.getUsername();
        avatar = member.getAvatar();

        MemberDtoresponse memberDtoresponse = new MemberDtoresponse( username, avatar );

        return memberDtoresponse;
    }
>>>>>>> 687f76890d74108b5518f0d40dedf28f91bdc486
}
