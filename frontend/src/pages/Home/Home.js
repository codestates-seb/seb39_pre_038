import React, { useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import axios from 'axios';
import { storge } from '../../utils/store';
import AsideNav from '../../components/AsideNav/AsideNav';
import styles from './Home.module.css';
import Questions from '../../components/Questions/Questions';
import Detail from '../../components/Detail/Detail';

function Home() {
  useEffect(() => {
    const responseURL = new URL(window.location.href);
    const code = responseURL.searchParams.get('code');
    if (!code) return;

    console.group('로그인 데이터');
    console.log('storge', storge.getData('API_TYPE'));
    console.log('API-Code', code);
    console.groupEnd();

    const data = {
      code,
    };

    axios
      .post(
        'http://ec2-13-124-94-129.ap-northeast-2.compute.amazonaws.com:8080/auth/user',
        data,
      )
      .then((res) => console.log(res.statusText))
      .catch((err) => console.log(err.message));
  }, []);

  return (
    <main className={styles.container}>
      <AsideNav />
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/:id" element={<Detail />} />
      </Routes>
    </main>
  );
}

export default Home;
