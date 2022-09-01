import React, { useRef, useEffect, useState } from 'react';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';
import '@toast-ui/editor/dist/toastui-editor.css';
// import Editor from '../../components/Editor/Editor';
import AsideNav from '../../components/AsideNav/AsideNav';
import style from './Replies.module.css';

function Replies() {
  const [Title, setTitle] = useState();
  const [queDate, setQueDate] = useState();
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState();
  const [totalAnswer, setTotalAnswer] = useState();
  const [queAvatarName, setQuesAvatarName] = useState();
  const [ansAvatarName, setAnsAvatarName] = useState();
  const [queAvatarImgUrl, setQueAvatarImgUrl] = useState();
  const [ansAvatarImgUrl, setAnsAvatarImgUrl] = useState();

  useEffect(() => {
    axios
      .get('http://localhost:8080/posts')
      .then((res) => {
        // console.log(res.data);
        setTitle(res.data[0].question_title);
        setQueDate(res.data[0].question_date);
        setQuestion(res.data[0].question_content);
        setQuesAvatarName(res.data[0].avatarName);
        setQueAvatarImgUrl(res.data[0].avatarImg);
        setTotalAnswer(res.data[0].answer);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  useEffect(() => {
    axios.get('http://localhost:8080/answer').then((res) => {
      // console.log(res.data);
      setAnswer(res.data[0].reply_content);
      // setAnsId(res.data.length);
      setAnsAvatarName(res.data[0].avatarImg);
      setAnsAvatarImgUrl(res.data[0].avatarImg);
    });
    // .catch((e) => console.log('somethign wrong:', e));
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
        content: contentMark,
        replyId: 1,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const AnswerDeleting = () => {
    // axios.delete(`http://localhost:8080/answer/${ansId}`);
  };

  const QuestionDeleting = () => {
    // axios.delete(`http://localhost:8080/posts/${queId}`);
  };

  return (
    <main className={style.container}>
      <AsideNav />

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
              {ansAvatarImgUrl} {ansAvatarName}
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
