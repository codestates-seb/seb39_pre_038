import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './Nav.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';
import SpriteIcon from '../SpriteIcon/SpriteIcon';

/* 햄버거 아이콘 비활성화
  const [toggle, setToggle] = useState(false);
  const toggleHamberger = () => setToggle(!toggle);
    <div
      className={toggle ? styles.hamberger_on : styles.hamberger_off}
      onClick={toggleHamberger}
      aria-hidden="true"
      role="button"
      tabIndex={0}
      >
        <span />
    </div>
*/

function Nav() {
  const [isLogin] = useState(false);

  const createUserScreen = () => {
    return (
      <React.Fragment key={null}>
        <img className={styles.avatar} alt="avatar" />
        <button className={styles.signBtn} type="button">
          Logout
        </button>
      </React.Fragment>
    );
  };

  const createGuestScreen = () => {
    return (
      <React.Fragment key={null}>
        <Link to="/login">
          <button className={styles.loginBtn} type="button">
            Login
          </button>
        </Link>
        <Link to="/signup">
          <button className={styles.signBtn} type="button">
            SignUp
          </button>
        </Link>
      </React.Fragment>
    );
  };

  return (
    <nav className={styles.container}>
      <Link to="/">
        <div className={styles.logo}>
          <SpriteIcon name="logo" />
        </div>
        <div className={styles.svgIcon}>
          <SvgIcon name="stackOverFlow" width={25} height={30} />
        </div>
      </Link>

      <ol className={styles.liWrap}>
        <li>About</li>
        <li>Products</li>
        <li>For Teams</li>
      </ol>

      <input className={styles.navInput} type="text" placeholder="Search..." />
      {isLogin ? createUserScreen() : createGuestScreen()}
    </nav>
  );
}

export default Nav;
