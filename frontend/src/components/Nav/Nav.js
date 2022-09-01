import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './Nav.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';
import SpriteIcon from '../SpriteIcon/SpriteIcon';

function Nav() {
  const [toggle, setToggle] = useState(false);
  const toggleHamberger = () => setToggle(!toggle);

  return (
    <nav className={styles.container}>
      <div
        className={toggle ? styles.hamberger_on : styles.hamberger_off}
        onClick={toggleHamberger}
        aria-hidden="true"
        role="button"
        tabIndex={0}
      >
        <span />
      </div>

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

      <div className={styles.navInput}>
        <input type="text" placeholder="Search..." />
        <SvgIcon name="serach" />
      </div>

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
    </nav>
  );
}

export default Nav;
