import React from 'react';
import SocialButtons from '../../components/SocialButtons/SocialButtons';
import SvgIcon from '../../components/SvgIcon/SvgIcon';
import styles from './SignUp.module.css';

function SignUp() {
  return (
    <main className={styles.container}>
      <section className={styles.textContent}>
        <h1>Join the Stack Overflow community</h1>
        <div>
          <SvgIcon name="question" />
          Get unstuck — ask a question
        </div>
        <div>
          <SvgIcon name="arrow" />
          Unlock new privileges like voting and commenting
        </div>
        <div>
          <SvgIcon name="tag" />
          Save your favorite tags, filters, and jobs
        </div>
        <div>
          <SvgIcon name="trophy" />
          Earn reputation and badges
        </div>

        <div className={styles.textFooter}>
          <div>
            Collaborate and share knowledge with a private group for FREE.
          </div>
          <em>Get Stack Overflow for Teams free for up to 50 users.</em>
        </div>
      </section>

      <section className={styles.btnContent}>
        <h1>
          <p>Create your Stack Overflow account.</p>
          <p>It’s free and only takes a minute.</p>
        </h1>
        <SocialButtons />
      </section>
    </main>
  );
}

export default SignUp;
