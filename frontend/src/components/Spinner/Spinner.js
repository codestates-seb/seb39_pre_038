import React from 'react';
import spinnerSrc from '../../img/spinner.gif';
import styles from './Spinner.module.css';

function Spinner() {
  return (
    <div className={styles.container}>
      <img src={spinnerSrc} alt="spinner" />
    </div>
  );
}

export default Spinner;
