import React from 'react';
import styles from './AskQuestion.module.css';

import Editor from '../Editor/Editor';

function AskQuestion() {
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
            />
          </div>

          <div className={styles.editorBody}>
            <h3>Body</h3>
            <p>
              Include all the information someone would need to answer you
              question
            </p>
            <Editor type="write" previewStyle="vertical" height="300px" />
          </div>
          <Editor type="View" />
        </div>
        <button className={styles.postBtn} type="button">
          Review your question
        </button>
      </div>
    </section>
  );
}

export default AskQuestion;

/*
  <Editor
    type="write"
    previewStyle="vertical"
    height="400px"
    initialEditType="markdown"
  />
*/
