import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';

function Questions() {
  const total = 9;
  const [currentPage, setCurrentPage] = useState(1);
  const navigate = useNavigate();
  const handleAskBtnOnClick = () => {
    navigate('/ask');
  };

  useEffect(() => {
    // 통신문
    console.log(currentPage);
  }, [currentPage]);

  return (
    <section className={styles.content}>
      <div className={styles.titleWrap}>
        <div className={styles.mainber}>
          <h1>All Questions</h1>
          <button type="button" onClick={handleAskBtnOnClick}>
            Ask Question
          </button>
        </div>
        <div className={styles.subber}>
          <em>22,960,505 questions</em>
          <button type="button">Filter</button>
        </div>
      </div>

      <div className={styles.questions}>
        <Question />
      </div>

      <Pagination
        total={total}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </section>
  );
}

export default Questions;
