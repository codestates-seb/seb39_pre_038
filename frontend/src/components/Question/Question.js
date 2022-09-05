import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Question.module.css';

const defaultData = {
  title: 'Using a button hover to increase margin with javascript',
  content: `Im trying to append parts of a dataframe to a json file in order to
  efficiently store new data in already existing files. I tried the
  following (and many other things), but this does not lead to a nicely
  readable json file.`,
  date: '45 secs ago',
  member: {
    username: 'YHJ96',
    avatar: '',
  },
  replies: 0,
};

function Question({ path = '', userData = defaultData }) {
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
        <div>{userData.replies} answer</div>
        <div>26 views</div>
      </div>
      <div className={styles.content}>
        <h2>{userData.title}</h2>
        <p>{userData.content}</p>
        <div>
          <img src={userData.member.avatar} alt="avatar" />
          <span className={styles.userId}>{userData.member.username}</span>
          <span className={styles.timeAgo}>{userData.date}</span>
        </div>
      </div>
    </div>
  );
}

export default Question;
