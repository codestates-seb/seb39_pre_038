import React, { useState } from 'react';
import styles from './Reply.module.css';
import Icon from '../SpriteIcon/SpriteIcon';

function Reply({
  answer,
  AnswerDeleting,
  ansDate,
  ansAvatarImgUrl,
  ansAvatarName,
  editing,
}) {
  const [visible, setVisible] = useState(false);

  return (
    <article className={styles.reply}>
      <div className={styles.vote}>
        <button className={styles.voteBtn} type="button" aria-label="vote">
          <Icon name="arrowUp" />
        </button>

        <div className={styles.voteCount}>0</div>

        <button className={styles.voteBtn} type="button" aria-label="vote">
          <Icon name="arrowDown" />
        </button>
      </div>
      <div className={styles.replyBody}>
        <p className={styles.body}>{answer}</p>

        <div className={styles.avatarInfo}>
          <div>
            <div className={styles.avatarDate}>
              <div>
                <button
                  className={styles.delete}
                  type="button"
                  onClick={() => {
                    // if ("회원이름" === ansAvatarName)
                    AnswerDeleting();
                  }}
                >
                  Delete
                </button>
                <button
                  className={styles.delete}
                  type="button"
                  onClick={() => {
                    // if ("회원이름" === ansAvatarName)
                    setVisible(!visible);
                  }}
                >
                  {visible ? 'Cancel' : 'Edit'}
                </button>
              </div>
              {ansDate}
            </div>
            <div className={styles.deleteImgName}>
              <div>
                <img src={ansAvatarImgUrl} alt="avatar" /> {ansAvatarImgUrl}
                <span className={styles.name}> {ansAvatarName}</span>
              </div>
            </div>
            {visible && editing}
          </div>
        </div>
      </div>
    </article>
  );
}

export default Reply;
