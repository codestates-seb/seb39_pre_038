import React from 'react';
import styles from './SpriteIcon.module.css';

const type = {
  logo: {
    width: '150px',
    height: '30px',
    backgroundPosition: '0 -500px',
  },
  star_primary: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-42px -116px',
  },
  star: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-2px -116px',
  },
  arrowUp_primary: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-42px -166px',
  },
  arrowUp: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-2px -166px',
  },
  arrowDown_primary: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-42px -216px',
  },
  arrowDown: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-2px -216px',
  },
  checkMark_primary: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-42px -266px',
  },
  checkMark: {
    width: '36px',
    height: '36px',
    backgroundPosition: '-2px -266px',
  },
};

function SpriteIcon({ name = '' }) {
  return <div className={styles.iconContainer} style={type[name]} />;
}

export default SpriteIcon;
