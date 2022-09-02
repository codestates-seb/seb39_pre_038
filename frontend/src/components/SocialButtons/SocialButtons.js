import React from 'react';
import styles from './SocialButtons.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';
import { storge } from '../../utils/store';
import { GITHUB_CLIEND_ID, GOOGLE_CLIEND_ID } from '../../env';

const api = {
  google: {
    url: `https://accounts.google.com/o/oauth2/v2/auth?client_id=${GOOGLE_CLIEND_ID}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile&redirect_uri=http://127.0.0.1:3000/question`,
  },
  github: {
    url: `https://github.com/login/oauth/authorize?client_id=${GITHUB_CLIEND_ID}`,
  },
};

function SocialButtons() {
  const handleOnClick = (apiType) => (e) => {
    const { value } = e.target;
    const { url } = apiType[value];
    window.location.assign(url);
    storge.setData('API_TYPE', value);
  };

  return (
    <React.Fragment key={null}>
      <header>
        <SvgIcon name="stackOverFlow" />
      </header>

      <div
        className={styles.btnWrap}
        onClick={handleOnClick(api)}
        role="button"
        aria-hidden="true"
      >
        <button value="google" type="button">
          <SvgIcon name="google" />
          Log in with Google
        </button>
        <button value="github" type="submit">
          <SvgIcon name="gitHub" />
          Log in with Google
        </button>
        <button type="submit">
          <SvgIcon name="faceBook" />
          Log in with Google
        </button>
      </div>

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
