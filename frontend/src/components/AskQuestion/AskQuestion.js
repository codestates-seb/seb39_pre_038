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
            <p>안녕하세요</p>
            <input type="text" />
          </div>

          <div className={styles.editorBody}>
            <h3>Body</h3>
            <p>안녕하세요</p>
            <div className={styles.editor}>
              <Editor type="write" previewStyle="vertical" height="400px" />
            </div>
          </div>

          <div className={styles.view}>
            <Editor type="View" />
          </div>
        </div>
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
