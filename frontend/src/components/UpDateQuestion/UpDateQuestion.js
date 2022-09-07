import React, { useEffect, useRef } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { PATCH_QUESTION } from '../../utils/api';
import styles from './UpDateQuestion.module.css';
import Editor from '../Editor/Editor';

function UpDateQuestion() {
  const navigate = useNavigate();
  const location = useLocation();
  const titleRef = useRef(null);
  const editorRef = useRef(null);
  const viewRef = useRef(null);

  const handleOnChange = () => {
    const value = editorRef.current.getInstance().getMarkdown();
    viewRef.current.getInstance().setMarkdown(value);
  };

  const handleOnClick = () => {
    const result = {
      title: titleRef.current.value,
      content: editorRef.current.getInstance().getMarkdown(),
      email: 'gmail@gmail.com',
    };
    axios
      .patch(PATCH_QUESTION(location.state.id), result)
      .then(() => navigate('/'))
      .catch(() => navigate('/'));
  };

  useEffect(() => {
    titleRef.current.value = location.state.title;
    editorRef.current.getInstance().setMarkdown(location.state.content, false);
  }, [location.state]);

  return (
    <section className={styles.container}>
      <div className={styles.content}>
        <div className={styles.contentHeader}>
          <h1>Update question</h1>
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
          Update your question
        </button>
      </div>
    </section>
  );
}

export default UpDateQuestion;
