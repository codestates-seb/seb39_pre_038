import React from 'react';
import styles from './AsideNav.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';

function AsideNav() {
  return (
    <aside>
      <nav className={styles.container}>
        <ul>
          PUBLIC
          <li>
            <SvgIcon name="earth" />
            Questions
          </li>
          <li>Tags</li>
          <li>Users</li>
          <li>Companies</li>
        </ul>

        <ul>
          COLLECTIVES
          <li>
            <SvgIcon name="star" />
            Explore Collectives
          </li>
        </ul>

        <ul>
          TEAMS
          <li>
            <div className={styles.icon}>
              <SvgIcon name="basket" />
              <SvgIcon name="shield" />
            </div>
            Create free Team
          </li>
        </ul>
      </nav>
    </aside>
  );
}

export default AsideNav;
