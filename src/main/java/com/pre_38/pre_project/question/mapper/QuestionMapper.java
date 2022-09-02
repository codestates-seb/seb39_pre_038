package com.pre_38.pre_project.question.mapper;


import com.pre_38.pre_project.member.mapper.MemberMapper;
import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;
import java.util.stream.Collectors;

//uses 애트리뷰트는 다른 Mapper 사용할 수 있게 함
@Mapper(componentModel = "spring", uses = MemberMapper.class)
public interface QuestionMapper {
    MemberMapper instance = Mappers.getMapper(MemberMapper.class);
    Question questionPostToQuestion(QuestionDto.Post questionPost);
    Question questionPatchToQuestion(QuestionDto.Patch questionPatch);
    QuestionDto.response questionToQuestionResponse(Question question);
    default List<QuestionDto.responses> questionsToQuestionResponses(List<Question> questions) {
        return questions
                .stream()
                .map(question -> QuestionDto.responses
                        .builder()
                        .questionId(question.getQuestionId())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .date(question.getDate())
                        .member(instance.MemberToMemberDtoresponse(question.getMember()))
                        .replies(question.getReplies().size())
                        .build())
                .collect(Collectors.toList());
    }
}
