import React from 'react';
import reactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import './index.css';
import App from './App';

const container = document.getElementById('root');
reactDOM.render(
  <Router>
    <App />
  </Router>,
  container,
);
