import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import Editor from '../Editor/Editor';
import styles from './Replies.module.css';
import Reply from '../Reply/Reply';

function Replies({ id }) {
  const [totalAnswer, setTotalAnswer] = useState(0);
  const [replies, setReplies] = useState(null);
  const [isLoding, setIsLoding] = useState(true);
  const [error, setError] = useState('');
  const [toggle, setToggle] = useState(false);

  const viewRef = useRef();
  const editorRef = useRef();

  const tg = () => {
    setToggle(!toggle);
  };

  const onChangeIntroFunction = () => {
    const value = editorRef.current.getInstance().getHTML();
    viewRef.current.getInstance().setMarkdown(value);
  };

  useEffect(() => {
    axios
      .get(`/questions/${id}`)
      .then((res) => {
        console.log(res.data.data.replies);
        setTotalAnswer(res.data.data.replies.length);
        setReplies(res.data.data.replies);
        setIsLoding(false);
      })
      .catch(() => {
        setIsLoding(false);
        setError('error!');
      });
  }, [toggle, id]);

  if (isLoding) {
    return <div>IsLoding</div>;
  }
  if (error) {
    return <div>Error</div>;
  }

  const posting = () => {
    axios
      .post('/questions/replies', {
        content: editorRef.current.getInstance().getHTML(),
        email: 'gmail@gmail.com',
        questionId: id,
      })
      .then((response) => {
        console.log(response);
        setToggle(!toggle);
      })
      .catch((err) => {
        console.log(err);
      });
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
        {replies.map((res) => (
          <Reply
            answer={res.content}
            ansDate={res.date}
            ansAvatarImgUrl={res.avatar}
            ansAvatarName={res.email}
            replies={res.replies}
            replyId={res.replyId}
            id={id}
            tg={tg}
            key={res.replyId}
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
