import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Nav from './components/Nav/Nav';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Questions from './pages/Questions';
import Replies from './pages/Replies';

function App() {
  return (
    <React.Fragment key={null}>
      <Nav />
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
