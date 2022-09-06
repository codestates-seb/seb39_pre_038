import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import styles from './Detail.module.css';
import { DETAIL_GET_QUESTION, DELETE_QUESTION } from '../../utils/api';
import Editor from '../Editor/Editor';
import Replies from '../Replies/Replies';

function Detail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const editorRef = useRef(null);
  const viewRef = useRef(null);
  const [data, setData] = useState({});
  const [isLoding, setIsLoding] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    axios
      .get(DETAIL_GET_QUESTION(id))
      .then((res) => {
        setData(res.data);
        setIsLoding(false);
      })
      .catch((err) => {
        setIsLoding(false);
        setError(err.message);
      });
  }, [id]);

  if (isLoding) return <div>Loding</div>;
  if (error) return <div>{error}</div>;

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
      <h1>{data.data.title}</h1>
      <Editor ref={viewRef} initialValue={data.data.content} />
      <button type="button" onClick={haldeOnPatch}>
        Edit
      </button>
      <button type="button" onClick={handleOnDelete}>
        DELETE
      </button>
      <Editor ref={editorRef} type="write" height="300px" />
      <Replies id={id} />
    </section>
  );
}

export default Detail;
