import React from 'react';
// import { useParams, useNavigate } from 'react-router-dom';
import styles from './Detail.module.css';
// import Replies from '../Replies/Replies';

function Detail() {
  // const navigate = useNavigate();
  // const { id } = useParams();
  // 네비게이션 예외처리 Effect

  /*
  useEffect(() => {
    if (id === '고유 아이디') return;
    navigate('/');
  }, [id, navigate]);
  */

  return <section className={styles.content}>Replies</section>;
}

export default Detail;
