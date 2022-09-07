import React, { useRef, useEffect, useState } from 'react';
import axios from 'axios';
import Editor from '../Editor/Editor';
import styles from './Replies.module.css';
import Reply from '../Reply/Reply';
import { DETAIL_GET_QUESTION, POST_ANSWER } from '../../utils/api';

function Replies({ id }) {
  const [replies, setReplies] = useState([]);
  const [isLoding, setIsLoding] = useState(true);
  const [error, setError] = useState('');
  const [toggle, setToggle] = useState(false);

  const viewRef = useRef(null);
  const editorRef = useRef(null);

  const toggleRender = () => {
    setToggle(!toggle);
  };

  const onChangeIntroFunction = () => {
    const value = editorRef.current.getInstance().getMarkdown();
    viewRef.current.getInstance().setMarkdown(value);
  };

  useEffect(() => {
    axios
      .get(DETAIL_GET_QUESTION(id))
      .then((res) => {
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
    return <div>{error}</div>;
  }

  const posting = () => {
    axios
      .post(POST_ANSWER, {
        content: editorRef.current.getInstance().getMarkdown(),
        email: 'gmail@gmail.com',
        questionId: id,
      })
      .then(() => {
        setToggle(!toggle);
        editorRef.current.getInstance().setMarkdown('', false);
      })
      .catch(() => {
        return <div>Something went wrong</div>;
      });
  };

  return (
    <main className={styles.container}>
      <section className={styles.content}>
        <div className={styles.h3}>
          <h3>{replies.length} Answer</h3>
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
            toggleRender={toggleRender}
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
