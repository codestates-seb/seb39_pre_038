/* eslint-disable react/no-unescaped-entities */
import React from 'react';
import styles from './NotFound.module.css';
import SvgIcon from '../../components/SvgIcon/SvgIcon';

function NotFound() {
  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <SvgIcon name="notFound" />

        <div className={styles.textWrap}>
          <h1>Page not found</h1>
          <p>We're sorry we couldn't find the page you requested.</p>

          <div>
            <p>
              Try <em>searching for similar questions</em>
            </p>
            <p>
              Browse our <em>recent questions</em>
            </p>
            <p>
              Browse our <em>popular tags</em>
            </p>
            <p>
              If you feel something is missing that should be here,
              <em>contact us.</em>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default NotFound;
