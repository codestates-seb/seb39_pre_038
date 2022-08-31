import React from 'react';
import styles from './Login.module.css';
import SocialButtons from '../../components/SocialButtons/SocialButtons';

function Login() {
  return (
    <main className={styles.container}>
      <SocialButtons />
    </main>
  );
}

export default Login;
