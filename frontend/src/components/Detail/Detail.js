import React, { useRef } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import styles from './Detail.module.css';
import { useFetch } from '../../hooks/index';
import { DETAIL_GET_QUESTION, DELETE_QUESTION } from '../../utils/api';
import Editor from '../Editor/Editor';
import Replies from '../Replies/Replies';
import SpriteIcon from '../SpriteIcon/SpriteIcon';
import Spinner from '../Spinner/Spinner';

function Detail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const editorRef = useRef(null);
  const viewRef = useRef(null);
  const { data, isLoding, error } = useFetch(DETAIL_GET_QUESTION(id));

  if (isLoding) return <Spinner />;
  if (error) {
    navigate('/404');
    return <div>Error</div>;
  }

  const handleAskBtnOnClick = () => navigate('/ask');

  const handleOnDelete = () => {
    axios
      .delete(DELETE_QUESTION(id))
      .then(() => navigate('/'))
      .catch(() => navigate('/'));
  };

  const haldeOnPatch = () => {
    const value = editorRef.current.getInstance().getMarkdown();
    const ret = {
      title: data.data.title,
      content: value,
      email: 'gmail@gmail.com',
    };
    axios
      .patch(DELETE_QUESTION(id), ret)
      .then(() => viewRef.current.getInstance().setMarkdown(value))
      .catch((err) => console.log(err));
  };

  return (
    <section className={styles.content}>
      <div className={styles.questionWrap}>
        <div className={styles.questionHeader}>
          <h1>{data.data.title}</h1>
          <button type="button" onClick={handleAskBtnOnClick}>
            Ask Question
          </button>
        </div>
        <div className={styles.date}>
          <span>
            Asked <em>today</em>
          </span>
          <span>
            Modified <em>today</em>
          </span>
          <span>
            Viewed <em>7 times</em>
          </span>
        </div>
        <div className={styles.mainbar}>
          <div className={styles.votecell}>
            <SpriteIcon name="arrowUp" />
            <span>19</span>
            <SpriteIcon name="arrowDown" />
          </div>

          <div className={styles.viewWrap}>
            <Editor ref={viewRef} initialValue={data.data.content} />

            <div className={styles.btnWrap}>
              <button type="button" onClick={handleOnDelete}>
                Delete
              </button>
              <button type="button" onClick={haldeOnPatch}>
                Edit
              </button>
            </div>

            <div className={styles.editor} style={{ display: 'none' }}>
              <Editor ref={editorRef} type="write" height="300px" />
            </div>
          </div>
        </div>
      </div>
      <Replies id={id} />
    </section>
  );
}

export default Detail;
