import React from 'react';
import { Routes, Route } from 'react-router-dom';
import AsideNav from '../../components/AsideNav/AsideNav';
import styles from './Home.module.css';
import Questions from '../../components/Questions/Questions';
import Detail from '../../components/Detail/Detail';

function Home() {
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
