import React, { useState, useRef } from 'react';
import axios from 'axios';
import styles from './Reply.module.css';
import Icon from '../SpriteIcon/SpriteIcon';
import Editor from '../Editor/Editor';

function Reply({
  answer,
  ansDate,
  ansAvatarImgUrl,
  ansAvatarName,
  id,
  replyId,
  tg,
}) {
  const [visible, setVisible] = useState(false);
  const viewerRef = useRef();
  const editRef = useRef();

  const patch = () => {
    axios
      .patch(`/questions/${id}/${replyId}`, {
        content: editRef.current.getInstance().getHTML(),
        email: 'gmail@gmail.com',
      })
      .then(() => {
        tg();
        setVisible(false);
      });
  };

  const AnswerDeleting = () => {
    axios.delete(`/questions/${id}/${replyId}`).then(() => {
      tg();
    });
  };

  const onChangeEdit = () => {
    const value = editRef.current.getInstance().getHTML();
    viewerRef.current.getInstance().setMarkdown(value);
  };

  const editing = (
    <div className={styles.editor}>
      <div>
        <Editor
          type="write"
          previewStyle="vertical"
          height="300px"
          initialEditType="markdown"
          ref={editRef}
          onChange={onChangeEdit}
        />
      </div>
      <div>
        <Editor type="view" ref={viewerRef} />
      </div>
      <button
        className={styles.btn}
        type="button"
        aria-label="Editing"
        onClick={() => {
          patch();
        }}
      >
        Eidt My Answer
      </button>
    </div>
  );

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

export default React.memo(Reply);
