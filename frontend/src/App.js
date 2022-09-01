import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Nav from './components/Nav/Nav';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Questions from './pages/Questions/Questions';
import AskQuestion from './components/AskQuestion/AskQuestion';

function App() {
  return (
    <React.Fragment key={null}>
      <Nav />
      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/ask" element={<AskQuestion />} />
      </Routes>
    </React.Fragment>
  );
}

export default App;
