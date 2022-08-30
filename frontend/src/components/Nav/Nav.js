import React from 'react';
import { Link } from 'react-router-dom';
import styles from './Nav.module.css';
import SpriteIcon from '../SpriteIcon/SpriteIcon';

function Nav() {
  return (
    <nav className={styles.container}>
      <SpriteIcon name="logo">
        <Link to="/">Questions</Link>
      </SpriteIcon>

      <ol>
        <li>About</li>
        <li>Products</li>
        <li>For Teams</li>
      </ol>

      <input type="text" />
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
