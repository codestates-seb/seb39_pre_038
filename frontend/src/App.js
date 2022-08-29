import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Questions from './pages/Questions';
import Replies from './pages/Replies';

function App() {
  return (
    <React.Fragment key={null}>
      <nav>
        <Link to="/">
          <li>Questions</li>
        </Link>
        <Link to="/login">
          <li>Login</li>
        </Link>
        <Link to="/singup">
          <li>SignUp</li>
        </Link>
        <Link to="/replies">
          <li>Replies</li>
        </Link>
      </nav>
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/login" element={<Login />} />
        <Route path="/singup" element={<SignUp />} />
        <Route path="/replies" element={<Replies />} />
      </Routes>
    </React.Fragment>
  );
}

export default App;
