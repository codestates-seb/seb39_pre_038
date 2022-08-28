package com.pre_38.pre_project.question.service;

import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //요구사항 2.1 + 2.6
    public Page<Question> findQuestions(int page, int size){
        return questionRepository.findAll(PageRequest.of(page,size, Sort.by("questionId").descending()));
    }

    //요구사항 2.2
    public Question createQuestion(Question question){

        return null;
    }

    //요구사항 2.3
    public void deleteQuestion(long questionId){

    }

    //요구사항 2.5
    public Question findQuestion(long questionId){
        return null;
    }
}
