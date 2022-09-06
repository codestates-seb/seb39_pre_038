import React, { useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { POST_QUESTION } from '../../utils/api';
import styles from './AskQuestion.module.css';
import Editor from '../Editor/Editor';

function AskQuestion() {
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const editorRef = useRef(null);
  const viewRef = useRef(null);

  const handleOnChange = () => {
    const value = editorRef.current.getInstance().getMarkdown();
    viewRef.current.getInstance().setMarkdown(value);
  };

  const handleOnClick = () => {
    const data = {
      title: titleRef.current.value,
      content: editorRef.current.getInstance().getMarkdown(),
      email: 'gmail@gmail.com',
    };
    axios
      .post(POST_QUESTION, data)
      .then(() => navigate('/'))
      .catch(() => navigate('/'));
  };

  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <div className={styles.contentHeader}>
          <h1>Ask a public question</h1>
        </div>
        <div className={styles.editorWrap}>
          <div className={styles.editorHeader}>
            <h3>Title</h3>
            <p>
              Be specific and imagine you`re asking a question to another person
            </p>
            <input
              type="text"
              placeholder="e.g is there an R function someone would need to answer your question"
              ref={titleRef}
            />
          </div>

          <div className={styles.editorBody}>
            <h3>Body</h3>
            <p>
              Include all the information someone would need to answer you
              question
            </p>
            <Editor
              type="write"
              height="300px"
              ref={editorRef}
              onChange={handleOnChange}
            />
          </div>
          <Editor ref={viewRef} />
        </div>
        <button
          className={styles.postBtn}
          type="button"
          onClick={handleOnClick}
        >
          Review your question
        </button>
      </div>
    </section>
  );
}

export default AskQuestion;
