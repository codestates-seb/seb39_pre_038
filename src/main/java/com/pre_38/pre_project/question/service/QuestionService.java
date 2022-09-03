package com.pre_38.pre_project.question.service;

import com.pre_38.pre_project.exception.BusinessLogicException;
import com.pre_38.pre_project.exception.ExceptionCode;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //요구사항 2.1 + 2.6
    public Page<Question> findQuestions(int page, int size){
        //페이징 처리하여 리턴
        return questionRepository.findAll(PageRequest.of(page,size, Sort.by("questionId").descending()));
    }

    //요구사항 2.2
    public Question createQuestion(Question question){
//        게시물 중복 작성 추가 가능
//        verifyExistsTitle(question.getTitle());
        return questionRepository.save(question);
    }

    //요구사항 2.3
    public void deleteQuestion(long questionId){
        //존재하는지 확인하는 메서드 확인후 해당 엔티티를 가져옴
        Question question = findVerifiedQuestion(questionId);
        //엔티티로 삭제
        questionRepository.delete(question);
    }

    //요구사항 2.5
    public Question findQuestion(long questionId){
        //존재하는지 확인하는 메서드 확인후 해당 엔티티를 바로 리턴
        return findVerifiedQuestion(questionId);
    }

    public Question updateQuestion(Question question, long questionId){
        //새로운 값으로 치환
        Question findQuestion = findVerifiedQuestion(questionId);

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));

        return questionRepository.save(findQuestion);
    }


    //중복 확인하는 메서드
    private void verifyExistsTitle(String title){
        Optional<Question> question = questionRepository.findByTitle(title);
        if(question.isPresent())
            throw new BusinessLogicException(ExceptionCode.QUESTION_EXISTS);
    }

    //테이블에서 존재하는지 확인하는 메서드
    public Question findVerifiedQuestion(long questionId){
        //Id 값으로 검색
        Optional<Question> optionalQuestion =
                questionRepository.findById(questionId);
        //존재 여부 확인
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

}
