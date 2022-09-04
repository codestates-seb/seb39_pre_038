import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import Editor from '../Editor/Editor';
import style from './Replies.module.css';
import Reply from '../Reply/Reply';

function Replies() {
  const [totalAnswer, setTotalAnswer] = useState();
  const [queId, setQueId] = useState();
  const [ansId, setAnsId] = useState();
  const [replies, setReplies] = useState();

  const viewRef = useRef();
  const editorRef = useRef();
  const viewerRef = useRef();
  const editRef = useRef();

  // 미리보기 기능

  // 1. Writer의 onChange 이벤트를 생성한다. [X]
  // 2. Writer의 onChange가 일어날때 마다 Viewer의 값을 갱신한다. [X]
  // 3. Viewer의 Ref의 값에 접근해서 setMarkDown()의 메소드를 불어와서 값을 갱신한다. [X]

  const onChangeIntroFunction = () => {
    const value = editorRef.current.getInstance().getHTML();
    viewRef.current.getInstance().setMarkdown(value);
  };

  const onChangeEdit = () => {
    const value = editRef.current.getInstance().getHTML();
    viewerRef.current.getInstance().setMarkdown(value);
  };

  useEffect(() => {
    axios
      // .get(`/questions/${queId}`)
      .get('http://localhost:8080/data')
      .then((res) => {
        console.log(res.data);
        setQueId(res.data.questionId);
        setTotalAnswer(res.data.replies.length);
        setReplies(res.data.replies);
        setAnsId(res.data.replies.replyId);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

  // POST, UPDATE 기능

  // 1. POST 버튼을 눌렀을 때 onClick() 이벤트를 생성한다. [X]
  // 2. POST의 body에 데이터를 보내는데 content: WriterRef.current.getInstance().getHTML(); 서버에 전송한다. [X]
  // 3. POST를 보내고 받아온 응답 데이터 state에 갱신한다. [X]
  // 4. UPDATE 게시물의 배열 인덱스에 접근해서 해당 content의 내용을 내가 작성한  WriterRef.current.getInstance().getHTML()로 갱신한다. [X]
  // 5. useEffect(() => { 새로고침하면 어차피 그대로 남아있습니다 저희가 서버에 전송했으니깐요 }, []) [X]

  const posting = () => {
    axios
      .post('/questions/replies', {
        content: editorRef.current.getInstance().getHTML(),
        email: 'email',
        questionId: queId,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    window.location.reload();
  };

  const patch = () => {
    axios.patch(`/questions/${queId}${ansId}`, {
      content: editRef.current.getInstance().getHTML(),
      email: 'emial',
    });
  };

  const editing = (
    <div className={style.editor}>
      <div>
        <Editor
          type="write"
          previewStyle="vertical"
          height="300px"
          initialEditType="markdown"
          ref={editRef}
          onChange={onChangeEdit}
        />
      </div>
      <div>
        <Editor type="view" ref={viewerRef} />
      </div>
      <button
        className={style.btn}
        type="button"
        aria-label="Editing"
        onClick={() => {
          patch();
        }}
      >
        Eidt My Answer
      </button>
    </div>
  );

  const AnswerDeleting = () => {
    axios.delete(`/questions/${queId}/${ansId}`);
    window.location.reload();
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
        {replies &&
          replies.map((res) => (
            <Reply
              answer={res.content}
              AnswerDeleting={AnswerDeleting}
              editing={editing}
              ansDate={res.date}
              ansAvatarImgUrl={res.avatar}
              ansAvatarName={res.email}
            />
          ))}

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
