import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './AsideNav.module.css';
import SvgIcon from '../SvgIcon/SvgIcon';

function AsideNav() {
  const navigate = useNavigate();
  const handleOnClick = () => navigate('/');
  return (
    <aside>
      <nav className={styles.container}>
        <ul>
          PUBLIC
          <li onClick={handleOnClick} aria-hidden="true">
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
