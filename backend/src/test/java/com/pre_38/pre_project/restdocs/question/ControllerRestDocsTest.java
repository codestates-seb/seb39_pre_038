package com.pre_38.pre_project.restdocs.question;


import com.google.gson.Gson;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.service.MemberService;
import com.pre_38.pre_project.question.controller.QuestionController;
import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.question.mapper.QuestionMapper;
import com.pre_38.pre_project.question.service.QuestionService;
import com.pre_38.pre_project.reply.controller.ReplyController;
import com.pre_38.pre_project.reply.dto.ReplyDto;
import com.pre_38.pre_project.reply.entity.Reply;
import com.pre_38.pre_project.reply.mapper.ReplyMapper;
import com.pre_38.pre_project.reply.service.ReplyService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {QuestionController.class, ReplyController.class})
@MockBean(JpaMetamodelMappingContext.class)
@WithMockUser
@AutoConfigureRestDocs
public class ControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper mapper;
    @MockBean
    private MemberService memberService;
    @MockBean
    private ReplyService replyService;
    @MockBean
    private ReplyMapper replyMapper;

    @Autowired
    private Gson gson;

    @Test
    public void getQuestionsTest() throws Exception {
        //given
        int page = 1;
        int size = 10;

        Question question1 = new Question(1L,"아무제목1","아무내용1",0);
        Question question2 = new Question(22L,"나도 모르는 제목2","나도 모르는 내용 2",2);
        Question question3 = new Question(333L,"매우 뛰어난 양질의 제목3","매우 뛰어난 양질의 정보3",333);

        Member member1 = new Member(123L,"초보","123", "naver@naver.com",LocalDateTime.now());
        Member member2 = new Member(456L,"중수","abc","gmail@gmail.com", LocalDateTime.now());
        Member member3 = new Member(789L,"고수","q1w2e3", "youtube@youtube.com",LocalDateTime.now());

        question1.setMember(member1);
        question2.setMember(member2);
        question3.setMember(member3);

        Page<Question> pageQuestions = new PageImpl<>(
                List.of(question1,question2,question3), PageRequest.of(page,size, Sort.by("questionId").descending()),3);

        List<ReplyDto.response> replies1 = List.of(
                new ReplyDto.response(1L,"댓글1",LocalDateTime.now(),0,member1)
        );

        List<ReplyDto.response> replies2 = List.of(
                new ReplyDto.response(2L,"댓글2",LocalDateTime.now(),5,member2),
                new ReplyDto.response(3L,"댓글3",LocalDateTime.now(),2,member3)
        );

        List<QuestionDto.response> responses = List.of(
                new QuestionDto.response(question1.getQuestionId(),question1.getTitle(),question1.getContent(),question1.getDate(),question1.getVotes(),member1,replies1),
                new QuestionDto.response(question2.getQuestionId(),question2.getTitle(),question2.getContent(),question2.getDate(),question2.getVotes(),member2,replies2),
                new QuestionDto.response(question3.getQuestionId(),question3.getTitle(),question3.getContent(),question3.getDate(),question3.getVotes(),member3,new ArrayList<>())
        );

        given(questionService.findQuestions(Mockito.anyInt(),Mockito.anyInt())).willReturn(new PageImpl<>(List.of()));
        given(mapper.questionsToQuestionResponses(Mockito.anyList())).willReturn(responses);

        String pages = String.valueOf(page);
        String sizes = String.valueOf(size);
        MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page",pages);
        queryParams.add("size",sizes);


        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/")
                                .accept(MediaType.APPLICATION_JSON)
                                .params(queryParams)
//                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-questions",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지당 회원 수")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("질문 본문"),
                                        fieldWithPath("data[].date").type(JsonFieldType.STRING).description("게시 시간"),
                                        fieldWithPath("data[].votes").type(JsonFieldType.NUMBER).description("게시 시간"),

                                        fieldWithPath("data[].member").type(JsonFieldType.OBJECT).description("회원 정보"),
                                        fieldWithPath("data[].member.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].member.name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data[].member.avatar").type(JsonFieldType.STRING).description("회원 아바타"),
                                        fieldWithPath("data[].member.date").type(JsonFieldType.STRING).description("가입 날짜"),
                                        fieldWithPath("data[].member.email").type(JsonFieldType.STRING).description("회원 이메일"),

                                        fieldWithPath("data[].replies[]").type(JsonFieldType.ARRAY).description("답글"),
                                        fieldWithPath("data[].replies[].replyId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data[].replies[].content").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("data[].replies[].date").type(JsonFieldType.STRING).description("답글 작성 일자"),
                                        fieldWithPath("data[].replies[].votes").type(JsonFieldType.NUMBER).description("답글 추천 수"),

                                        fieldWithPath("data[].replies[].member").type(JsonFieldType.OBJECT).description("답글단 회원 정보"),
                                        fieldWithPath("data[].replies[].member.memberId").type(JsonFieldType.NUMBER).description("답글단 회원 식별자"),
                                        fieldWithPath("data[].replies[].member.name").type(JsonFieldType.STRING).description("답글단 회원 이름"),
                                        fieldWithPath("data[].replies[].member.avatar").type(JsonFieldType.STRING).description("답글단 회원 아바타"),
                                        fieldWithPath("data[].replies[].member.date").type(JsonFieldType.STRING).description("답글단 가입 날짜"),
                                        fieldWithPath("data[].replies[].member.email").type(JsonFieldType.STRING).description("답글단 회원 이메일"),

                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 게시글 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")

                                )
                        )
                ));
    }


    @Test
    public void postQuestionTest() throws Exception{
        //given
        QuestionDto.Post post = new QuestionDto.Post("아무제목","아무내용","아무유저");
        String content = gson.toJson(post);
        QuestionDto.response responseDto =
                new QuestionDto.response(1L,"아무제목","아무내용",LocalDateTime.now(),0,new Member(1L,"아무유저","1234",
                        "naver@naver.com",LocalDateTime.now()),new ArrayList<>());

        given(mapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());
        given(memberService.findMember(Mockito.anyString())).willReturn(new Member());
        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(new Question());
        given(mapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/ask")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(post.getTitle()))
                .andExpect(jsonPath("$.data.content").value(post.getContent()))
                .andExpect(jsonPath("$.data.member.name").value(post.getName()))
                .andDo(document(
                        "post-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("게시글 본문"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("게시글 작성자")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("게시글 본문"),
                                        fieldWithPath("data.date").type(JsonFieldType.STRING).description("작성 일자"),
                                        fieldWithPath("data.votes").type(JsonFieldType.NUMBER).description("게시글 추천수"),
                                        fieldWithPath("data.member").type(JsonFieldType.OBJECT).description("게시글 작성자"),
                                        fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 아이디"),
                                        fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                        fieldWithPath("data.member.avatar").type(JsonFieldType.STRING).description("작성자 아바타"),
                                        fieldWithPath("data.member.date").type(JsonFieldType.STRING).description("작성자 가입 날짜"),
                                        fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),
                                        fieldWithPath("data.replies[]").type(JsonFieldType.ARRAY).description("답글")
                                )
                        )
                ));
    }

    @Test
    public void deleteQuestion() throws Exception{
        //given
        long questionId = 1L;
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/questions/{question-id}",questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("question-id").description("게시글 식별자")
                        )
                ));
    }

    @Test
    public void getQuestion() throws Exception{
        //given
        long questionId = 1L;
        Member member1 = new Member(123L,"초보","123", "naver@naver.com",LocalDateTime.now());
        Member member2 = new Member(456L,"중수","abc","gmail@gmail.com", LocalDateTime.now());
        Member member3 = new Member(789L,"고수","q1w2e3", "youtube@youtube.com",LocalDateTime.now());

        ReplyDto.response reply1 = new ReplyDto.response(1L,"아무내용1",LocalDateTime.now(),0,member1);
        ReplyDto.response reply2 = new ReplyDto.response(12L,"아무내용2",LocalDateTime.now(),3,member2);
        ReplyDto.response reply3 = new ReplyDto.response(34L,"아무내용3",LocalDateTime.now(),8,member3);





        QuestionDto.response responseDto =
                new QuestionDto.response(1L,"아무제목","아무내용",LocalDateTime.now(),0,new Member(1L,"아무유저","1234",
                        "naver@naver.com",LocalDateTime.now()),List.of(reply1,reply2,reply3));


        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/{question-id}",questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );
        //then


        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(responseDto.getTitle()))
                .andExpect(jsonPath("$.data.content").value(responseDto.getContent()))
                .andExpect(jsonPath("$.data.votes").value(responseDto.getVotes()))
                .andExpect(jsonPath("$.data.member.name").value(responseDto.getMember().getName()))
                .andExpect(jsonPath("$.data.member.avatar").value(responseDto.getMember().getAvatar()))
                .andExpect(jsonPath("$.data.member.email").value(responseDto.getMember().getEmail()))
                .andDo(document(
                        "get-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("question-id").description("게시글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("게시글 본문"),
                                        fieldWithPath("data.date").type(JsonFieldType.STRING).description("작성 일자"),
                                        fieldWithPath("data.votes").type(JsonFieldType.NUMBER).description("게시글 추천수"),

                                        fieldWithPath("data.member").type(JsonFieldType.OBJECT).description("게시글 작성자"),
                                        fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("작성자 아이디"),
                                        fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                        fieldWithPath("data.member.avatar").type(JsonFieldType.STRING).description("작성자 아바타"),
                                        fieldWithPath("data.member.date").type(JsonFieldType.STRING).description("작성자 가입 날짜"),
                                        fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("작성자 이메일"),

                                        fieldWithPath("data.replies").type(JsonFieldType.ARRAY).description("답글"),
                                        fieldWithPath("data.replies[].replyId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data.replies[].content").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("data.replies[].date").type(JsonFieldType.STRING).description("답글 작성 일자"),
                                        fieldWithPath("data.replies[].votes").type(JsonFieldType.NUMBER).description("답글 추천 수"),

                                        fieldWithPath("data.replies[].member").type(JsonFieldType.OBJECT).description("답글 회원"),
                                        fieldWithPath("data.replies[].member.memberId").type(JsonFieldType.NUMBER).description("답글 작성자 아이디"),
                                        fieldWithPath("data.replies[].member.name").type(JsonFieldType.STRING).description("답글 작성자 닉네임"),
                                        fieldWithPath("data.replies[].member.avatar").type(JsonFieldType.STRING).description("답글 작성자 아바타"),
                                        fieldWithPath("data.replies[].member.date").type(JsonFieldType.STRING).description("답글 작성자 가입 날짜"),
                                        fieldWithPath("data.replies[].member.email").type(JsonFieldType.STRING).description("답글 작성자 이메일")
                                )
                        )
                ));
    }

    //405에러 주의 : Post -> PathParameter 사용시 에러 (POST는 노출 절대 안됨!!)
    @Test
    public void postReplyTest() throws Exception{
        //given
        long replyId = 1L;
        long questionId = 1L;

        ReplyDto.Post post = new ReplyDto.Post("아무 댓글", "아무 유저",questionId);
        String content = gson.toJson(post);

        ReplyDto.response response = new ReplyDto.response(replyId,"아무 댓글",LocalDateTime.now(),1,new Member(1L,"아무 유저","1234",
                "naver@naver.com",LocalDateTime.now()));

        given(replyMapper.replyPostToReply(Mockito.any(ReplyDto.Post.class))).willReturn(new Reply());
        given(memberService.findMember(Mockito.anyString())).willReturn(new Member());
        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(replyService.createReply(Mockito.any(Reply.class))).willReturn(new Reply());
        given(replyMapper.replyToReplyResponse(Mockito.any(Reply.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/replies")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.content").value(response.getContent()))
                .andExpect(jsonPath("$.data.member.name").value(response.getMember().getName()))
                .andExpect(jsonPath("$.data.votes").value(response.getVotes()))
                .andDo(document(
                        "post-reply",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답글"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("답글 작성자"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("게시글 식별자")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.replyId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("답글 본문"),
                                        fieldWithPath("data.date").type(JsonFieldType.STRING).description("답글 작성시간"),
                                        fieldWithPath("data.votes").type(JsonFieldType.NUMBER).description("답글 추천 수"),

                                        fieldWithPath("data.member").type(JsonFieldType.OBJECT).description("답글 작성 회원"),
                                        fieldWithPath("data.member.memberId").type(JsonFieldType.NUMBER).description("답글 작성 아이디"),
                                        fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("답글 작성 닉네임"),
                                        fieldWithPath("data.member.avatar").type(JsonFieldType.STRING).description("답글 작성 아바타"),
                                        fieldWithPath("data.member.date").type(JsonFieldType.STRING).description("답글 작성 가입 날짜"),
                                        fieldWithPath("data.member.email").type(JsonFieldType.STRING).description("답글 작성 이메일")

                                )
                        )
                ));
    }

    @Test
    public void deleteReplyTest() throws Exception{
        //given
        long questionId = 1L;
        long replyId = 1L;

        doNothing().when(replyService).deleteReply(Mockito.anyLong());

        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/questions/{question-id}/{reply-id}",questionId,replyId)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-reply",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("question-id").description("게시글 식별자"),
                                parameterWithName("reply-id").description("답글 식별자")
                        )
                ));
    }

    @Test
    public void getRepliesTest() throws Exception{
        //given
        long questionId = 1L;
        int page = 1;
        int size = 10;
        List<ReplyDto.response> replies = List.of(
                new ReplyDto.response(1L,"아무 댓글",LocalDateTime.now(),1,new Member(11L,"아무 유저","1234",
                        "naver@naver.com",LocalDateTime.now())),
                new ReplyDto.response(2L,"아무 댓글",LocalDateTime.now(),1,new Member(22L,"아무 유저","1234",
                        "naver@naver.com",LocalDateTime.now())),
        new ReplyDto.response(3L,"아무 댓글",LocalDateTime.now(),1,new Member(33L,"아무 유저","1234",
                "naver@naver.com",LocalDateTime.now()))
        );

        given(replyService.findReplies(Mockito.anyLong(),Mockito.anyInt(),Mockito.anyInt())).willReturn(new PageImpl<>(List.of()));
        given(replyMapper.repliesToReplyResponses(Mockito.anyList())).willReturn(replies);

        String pages = String.valueOf(page);
        String sizes = String.valueOf(size);
        MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page",pages);
        queryParams.add("size",sizes);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/{question-id}/replies",questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .params(queryParams)
//                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-replies",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지당 회원 수")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("답글 목록"),
                                        fieldWithPath("data[].replyId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("답글 본문"),
                                        fieldWithPath("data[].date").type(JsonFieldType.STRING).description("답글 작성시간"),
                                        fieldWithPath("data[].votes").type(JsonFieldType.NUMBER).description("답글 추천 수"),

                                        fieldWithPath("data[].member").type(JsonFieldType.OBJECT).description("답글 작성 회원"),
                                        fieldWithPath("data[].member.memberId").type(JsonFieldType.NUMBER).description("답글 작성 아이디"),
                                        fieldWithPath("data[].member.name").type(JsonFieldType.STRING).description("답글 작성 닉네임"),
                                        fieldWithPath("data[].member.avatar").type(JsonFieldType.STRING).description("답글 작성 아바타"),
                                        fieldWithPath("data[].member.date").type(JsonFieldType.STRING).description("답글 작성 가입 날짜"),
                                        fieldWithPath("data[].member.email").type(JsonFieldType.STRING).description("답글 작성 이메일"),

                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 게시글 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")

                                )
                        )
                ));
    }
}
