import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GET_QUESTIONS } from '../../utils/api';
import { useFetch } from '../../hooks/index';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';
import Spinner from '../Spinner/Spinner';

function Questions() {
  const [currentPage, setCurrentPage] = useState(1);
  const { data, error, isLoding } = useFetch(
    GET_QUESTIONS(`?page=${currentPage}`),
  );
  const navigate = useNavigate();

  if (isLoding) return <Spinner />;
  if (error) {
    navigate('/404');
    return <div>Error</div>;
  }

  const handleAskBtnOnClick = () => {
    navigate('/ask');
  };

  const createQuestion = () => {
    return data.data.map((item) => {
      return (
        <Question
          key={item.questionId}
          path={item.questionId}
          userData={item}
        />
      );
    });
  };

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

      <div className={styles.questions}>{createQuestion()}</div>

      <Pagination
        total={data.pageInfo.totalPages}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </section>
  );
}

export default Questions;
