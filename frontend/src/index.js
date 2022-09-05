import React from 'react';
import reactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import axios from 'axios';
import './index.css';
import App from './App';

axios.defaults.withCredentials = true;
const container = document.getElementById('root');
reactDOM.render(
  <Router>
    <App />
  </Router>,
  container,
);
