import React, { useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
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
