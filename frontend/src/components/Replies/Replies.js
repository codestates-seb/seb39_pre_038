import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import Editor from '../Editor/Editor';
import style from './Replies.module.css';

function Replies() {
  const [ansDate, setAnsDate] = useState();
  const [answer, setAnswer] = useState();
  const [totalAnswer, setTotalAnswer] = useState();
  const [ansAvatarName, setAnsAvatarName] = useState();
  const [ansAvatarImgUrl, setAnsAvatarImgUrl] = useState();
  const [queId, setQueId] = useState();
  const [ansId, setAnsId] = useState();
  const [urAnswer, setUrAnswer] = useState();
  // const [replies, setReplies] = useState();

  const viewRef = useRef();
  const editorRef = useRef();

  const onChangeIntroFunction = () => {
    setUrAnswer(editorRef.current.getInstance().getMarkdown());
    viewRef.current.getInstance().setMarkdown(urAnswer);
  };

  // setAnswer(res.data.data.replies[i].content);
  // setAnsAvatarName(res.data.data.replies[i].member.email);
  // setAnsAvatarImgUrl(res.data.data.replies[i].member.avatar);
  // setAnsDate(res.data.data.replies[i].member.date);

  useEffect(() => {
    axios
      .get(`/questions/${queId}`)
      .then((res) => {
        console.log(res.data.data);
        setQueId(res.data.data.questionId);
        setTotalAnswer(res.data.data.replies.length);
        setAnsId(res.data.data.replies.replyId);
        for (let i = 0; i < res.data.data.replies.length; i += 1) {
          setAnswer(res.data.data.replies[i].content);
          setAnsAvatarName(res.data.data.replies[i].member.email);
          setAnsAvatarImgUrl(res.data.data.replies[i].member.avatar);
          setAnsDate(res.data.data.replies[i].member.date);
        }
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  const posting = () => {
    axios
      .post('/questions/replies', {
        content: urAnswer,
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

  const AnswerDeleting = () => {
    axios.delete(`/questions/${queId}/${ansId}`);
  };

  return (
    <main className={style.container}>
      <section className={style.content}>
        <div className={style.h3}>
          <h3>{totalAnswer} Answer</h3>
          <span>
            Sorted by:
            <select>
              <option>Newest</option>
            </select>
          </span>
        </div>

        <article className={style.reply}>
          <div className={style.vote}>
            <button className={style.voteBtn} type="button" aria-label="vote" />

            <div className={style.voteCount}>0</div>

            <button className={style.voteBtn} type="button" aria-label="vote" />
          </div>
          <div className={style.replyBody}>
            <p className={style.body}>
              {answer}Im trying to append parts of a dataframe to a json file in
              order to efficiently store new data in already existing files. I
              tried the following (and many other things), but this does not
              lead to a nicely readable json file what what what what v vwhat
              whatsss
            </p>

            <div className={style.avatarInfo}>
              <div>
                <div className={style.avatarDate}>
                  <button
                    className={style.delete}
                    type="button"
                    onClick={() => {
                      AnswerDeleting();
                    }}
                  >
                    Delete
                  </button>
                  {ansDate}2022-02-01
                </div>
                <div className={style.deleteImgName}>
                  <div>
                    <img alt=" " /> {ansAvatarImgUrl}
                    <span className={style.name}>Jake {ansAvatarName}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </article>

        <article>
          <h1 className={style.UrAnswer}>Your Answer</h1>

          <div>
            <Editor
              type="write"
              previewStyle="vertical"
              height="300px"
              initialEditType="markdown"
              ref={editorRef}
              onChange={onChangeIntroFunction}
            />
          </div>
          <div>
            <Editor type="view" ref={viewRef} />
          </div>

          <button
            className={style.btn}
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
