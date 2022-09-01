import React from 'react';
import AsideNav from '../../components/AsideNav/AsideNav';
import styles from './Questions.module.css';

function Questions() {
  return (
    <main className={styles.container}>
      <AsideNav />
      <section className={styles.content}>
        <div className={styles.mainber}>
          <h1>All Questions</h1>
          <button type="button">Ask Question</button>
        </div>
        <div className={styles.subber}>
          <em>22,960,505 questions</em>
          <button type="button">Filter</button>
        </div>

        <div className={styles.questions}>Question</div>
      </section>
    </main>
  );
}

export default Questions;
