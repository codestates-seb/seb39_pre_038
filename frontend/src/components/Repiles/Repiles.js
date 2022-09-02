import React, { useRef, useEffect, useState } from 'react';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';
import '@toast-ui/editor/dist/toastui-editor.css';
import style from './Repiles.module.css';
// import Editor from '../../components/Editor/Editor';

function Replies() {
  const [Title, setTitle] = useState();
  const [queDate, setQueDate] = useState();
  const [ansDate, setAnsDate] = useState();
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState();
  const [totalAnswer, setTotalAnswer] = useState();
  const [queAvatarName, setQuesAvatarName] = useState();
  const [ansAvatarName, setAnsAvatarName] = useState();
  const [queAvatarImgUrl, setQueAvatarImgUrl] = useState();
  const [ansAvatarImgUrl, setAnsAvatarImgUrl] = useState();
  const [queId, setQueId] = useState();
  const [ansId, setAnsId] = useState();

  useEffect(() => {
    axios
      .get('/questions/queId')
      .then((res) => {
        console.log(res.data.data);
        setQueId(res.data.data.questionId);
        setTitle(res.data.data.title);
        setQueDate(res.data.data.date);
        setQuestion(res.data.data.content);
        setQuesAvatarName(res.data.data.member.username);
        setQueAvatarImgUrl(res.data.data.member.avatar);
        setTotalAnswer(res.data.data.replies.length);
        setAnsId(res.data.data.replies.replyId);
        setAnswer(res.data.data.replies[0].content);
        setAnsAvatarName(res.data.data.replies[0].member.username);
        setAnsAvatarImgUrl(res.data.data.replies[0].member.avatar);
        setAnsDate(res.data.data.replies[0].member.date);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  const editorRef = useRef();

  const posting = () => {
    const editorIns = editorRef.current.getInstance();
    // const contentHtml = editorIns.getHTML();
    const contentMark = editorIns.getMarkdown();
    // console.log(contentHtml);
    console.log(contentMark);
    axios
      .post('/questions/replies', {
        content: 'test',
        username: '작성자3',
        questionId: 5,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const QuestionDeleting = () => {
    axios.delete(`/questions/${queId}`);
  };

  const AnswerDeleting = () => {
    axios.delete(`/questions/${queId}/${ansId}`);
  };

  return (
    <main className={style.container}>
      <section className={style.content}>
        <div className={style.underline}>
          <h1 className={`${style.h1} ${style.margin}`}>{Title}</h1>
          <div className={`${style.date} ${style.margin}`}>date{queDate}</div>
        </div>

        <article>
          <div>
            <button type="button" aria-label="vote" />0
            <button type="button" aria-label="vote" />
          </div>

          <p>{question}</p>

          <div className="info">
            <button
              type="button"
              onClick={() => {
                QuestionDeleting();
              }}
            >
              Delete
            </button>

            <span>
              {queAvatarImgUrl} {queAvatarName}
            </span>
          </div>
        </article>

        <div className="sorted">
          <h3>{totalAnswer} Answer</h3>
          <span>
            Sorted by:
            <select>
              <option>Newest</option>
            </select>
          </span>
        </div>

        <article>
          <div>
            <button type="button" aria-label="vote" />0
            <button type="button" aria-label="vote" />
          </div>
          <p>{answer}</p>
          <div>
            <button
              type="button"
              onClick={() => {
                AnswerDeleting();
              }}
            >
              Delete
            </button>

            <span>
              {ansDate} {ansAvatarImgUrl} {ansAvatarName}
            </span>
          </div>
        </article>

        <article>
          <h1>Your Answer</h1>
          <Editor
            ref={editorRef}
            previewStyle="vertical"
            height="300px"
            initialEditType="markdown"
            toolbarItems={[
              ['bold', 'italic'],
              ['link', 'quote', 'code', 'image', 'codeblock'],
              ['ol', 'ul', 'heading', 'hr'],
            ]}
          />
          <button
            type="button"
            aria-label="Posting"
            onClick={() => {
              posting();
            }}
          >
            Post Your Answer
          </button>
        </article>
      </section>
    </main>
  );
}

export default Replies;
