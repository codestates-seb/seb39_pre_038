import React, { useState } from 'react';
import style from './Reply.module.css';
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
    <article className={style.reply}>
      <div className={style.vote}>
        <button className={style.voteBtn} type="button" aria-label="vote">
          <Icon name="arrowUp" />
        </button>

        <div className={style.voteCount}>0</div>

        <button className={style.voteBtn} type="button" aria-label="vote">
          <Icon name="arrowDown" />
        </button>
      </div>
      <div className={style.replyBody}>
        <p className={style.body}>{answer}</p>

        <div className={style.avatarInfo}>
          <div>
            <div className={style.avatarDate}>
              <div>
                <button
                  className={style.delete}
                  type="button"
                  onClick={() => {
                    // if ("회원이름" === ansAvatarName)
                    AnswerDeleting();
                  }}
                >
                  Delete
                </button>
                <button
                  className={style.delete}
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
            <div className={style.deleteImgName}>
              <div>
                <img src={ansAvatarImgUrl} alt="avatar" /> {ansAvatarImgUrl}
                <span className={style.name}> {ansAvatarName}</span>
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
