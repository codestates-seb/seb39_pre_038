import React from 'react';
import styles from './SocialButtons.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';

function SocialButtons() {
  return (
    <React.Fragment key={null}>
      <SvgIcon name="stackOverFlow" />

      <form className={styles.btnWrap}>
        <button type="button">
          <SvgIcon name="google" />
          Log in with Google
        </button>
        <button type="button">
          <SvgIcon name="gitHub" />
          Log in with Google
        </button>
        <button type="button">
          <SvgIcon name="faceBook" />
          Log in with Google
        </button>
      </form>

      <footer className={styles.textWrap}>
        <div>
          Don’t have an account? <em>Sign up</em>
        </div>
        <div>
          Are you an employer?
          <em>Sign up on Talent</em>
          <SvgIcon name="share" className={styles.test} />
        </div>
      </footer>
    </React.Fragment>
  );
}

export default SocialButtons;
