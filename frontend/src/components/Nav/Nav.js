import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styles from './Nav.module.css';
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

      <SpriteIcon name="logo">
        <Link to="/">Questions</Link>
      </SpriteIcon>

<<<<<<< HEAD
      <ol className={styles.liWrap}>
=======
      <ol>
>>>>>>> main
        <li>About</li>
        <li>Products</li>
        <li>For Teams</li>
      </ol>

<<<<<<< HEAD
      <input className={styles.navInput} type="text" />
=======
      <input type="text" />
>>>>>>> main
      <Link to="/login">
        <button type="button">Login</button>
      </Link>
      <Link to="/singup">
        <button type="button">SignUp</button>
      </Link>
    </nav>
  );
}

export default Nav;
