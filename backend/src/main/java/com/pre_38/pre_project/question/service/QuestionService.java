package com.pre_38.pre_project.question.service;

import com.pre_38.pre_project.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class QuestionService {

    //요구사항 2.1 + 2.6
    public Page<Question> findQuestions(int page, int size){
        return null;
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
