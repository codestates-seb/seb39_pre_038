import React from 'react';
<<<<<<< HEAD
import reactDOM from 'react-dom';
=======
import reactDOM from 'react-dom/client';
>>>>>>> main
import { BrowserRouter as Router } from 'react-router-dom';
import './index.css';
import App from './App';

<<<<<<< HEAD
const container = document.getElementById('root');
reactDOM.render(
  <Router>
    <App />
  </Router>,
  container,
=======
const root = reactDOM.createRoot(document.getElementById('root'));
root.render(
  <Router>
    <App />
  </Router>,
>>>>>>> main
);
