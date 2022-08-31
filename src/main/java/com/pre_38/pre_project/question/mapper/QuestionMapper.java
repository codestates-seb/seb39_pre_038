package com.pre_38.pre_project.question.mapper;

import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post questionPost);
    Question questionPatchToQuestion(QuestionDto.Patch questionPatch);
    QuestionDto.response questionToQuestionResponse(Question question);
    List<QuestionDto.response> questionsToQuestionResponses(List<Question> questions);
}
