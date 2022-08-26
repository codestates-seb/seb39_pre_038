package com.pre_38.pre_project.reply.mapper;

import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    Reply replyPostToReply (ReplyDto.Post replyPost);
    Reply replyPatchToReply(ReplyDto.Patch replyPatch);
    ReplyDto.response replyToReplyResponse(Reply reply);
    List<ReplyDto.response> replysToReplyResponses(List<Reply> replys);
}

