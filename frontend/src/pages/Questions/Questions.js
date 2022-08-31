import React from 'react';
import AsideNav from '../../components/AsideNav/AsideNav';
import styles from './Questions.module.css';

function Questions() {
  return (
    <main className={styles.container}>
      <AsideNav />
      <section className={styles.content}>정보</section>
    </main>
  );
}

export default Questions;
