import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import Editor from '../Editor/Editor';
import styles from './Replies.module.css';
import Reply from '../Reply/Reply';

function Replies() {
  const [totalAnswer, setTotalAnswer] = useState(0);
  const [queId, setQueId] = useState(0);
  const [ansId, setAnsId] = useState(0);
  const [replies, setReplies] = useState(null);

  const viewRef = useRef();
  const editorRef = useRef();
  const viewerRef = useRef();
  const editRef = useRef();

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
      .get(`/questions/${queId}`)
      .then((res) => {
        console.log(res.data);
        setQueId(res.data.questionId);
        setTotalAnswer(res.data.replies.length);
        setReplies(res.data.replies);
        setAnsId(res.data.replies.replyId);
      })
      .catch((e) => console.log('somethign wrong:', e));
  }, []);

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
  };

  const patch = () => {
    axios.patch(`/questions/${queId}${ansId}`, {
      content: editRef.current.getInstance().getHTML(),
      email: 'emial',
    });
  };

  const editing = (
    <div className={styles.editor}>
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
        className={styles.btn}
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
  };

  return (
    <main className={styles.container}>
      <section className={styles.content}>
        <div className={styles.h3}>
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
          <h1 className={styles.UrAnswer}>Your Answer</h1>

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
            className={styles.btn}
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
