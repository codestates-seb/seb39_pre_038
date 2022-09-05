import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { GET_QUESTIONS } from '../../utils/api';
import styles from './Questions.module.css';
import Question from '../Question/Question';
import Pagination from '../Pagination/Pagination';

function Questions() {
  const [data, setData] = useState(null);
  const [error, setError] = useState('');
  const [isLoding, setIsLoding] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(GET_QUESTIONS(`?page=${currentPage}`))
      .then((res) => {
        setData(res.data);
        setIsLoding(false);
      })
      .catch((err) => {
        setError(err.message);
        setIsLoding(false);
      });
  }, [currentPage]);

  if (isLoding) return <div>Loding</div>;
  if (error) return <div>{error}</div>;

  const handleAskBtnOnClick = () => {
    navigate('/ask');
  };

  const createQuestion = () => {
    if (data === null) return null;
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
        total={data?.pageInfo.totalPages}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </section>
  );
}

export default Questions;
