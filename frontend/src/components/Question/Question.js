import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Question.module.css';

function Question({ path = '고유 아이디' }) {
  const navigate = useNavigate();
  // Detail 경로로 이동하기 위한 함수
  const handleOnClick = () => {
    navigate(`/question/${path}`);
  };
  return (
    <div
      className={styles.container}
      onClick={handleOnClick}
      role="button"
      aria-hidden="true"
    >
      <div className={styles.stats}>
        <div>0 votes</div>
        <div>2 answer</div>
        <div>26 views</div>
      </div>
      <div className={styles.content}>
        <h2>Using a button hover to increase margin with javascript</h2>
        <p>
          Im trying to append parts of a dataframe to a json file in order to
          efficiently store new data in already existing files. I tried the
          following (and many other things), but this does not lead to a nicely
          readable json file what what what what v vwhat whatsss
        </p>
        <div>
          <img alt="avatar" />
          <span className={styles.userId}>YHJ96</span>
          <span className={styles.timeAgo}>answered 45 secs ago</span>
        </div>
      </div>
    </div>
  );
}

export default Question;
