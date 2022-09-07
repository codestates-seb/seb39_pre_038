import React from 'react';
import { useNavigate } from 'react-router-dom';
import { randomRange, randomAvatar } from '../../utils/random';
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
  const votes = randomRange(1, 100);
  const views = randomRange(0, 1500);
  const handleOnClick = () =>
    navigate(`/question/${path}`, { state: { votes } });
  return (
    <div
      className={styles.container}
      onClick={handleOnClick}
      role="button"
      aria-hidden="true"
    >
      <div className={styles.stats}>
        <div>{votes} votes</div>
        <div>{userData.replies} answer</div>
        <div>{views} views</div>
      </div>
      <div className={styles.content}>
        <h2>{userData.title}</h2>
        <p>{userData.content}</p>
        <div>
          <img src={randomAvatar().avatar} alt="avatar" />
          <span className={styles.userId}>{randomAvatar().name}</span>
          <span className={styles.timeAgo}>{userData.date}</span>
        </div>
      </div>
    </div>
  );
}

export default Question;
