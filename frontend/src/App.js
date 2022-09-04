import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Nav from './components/Nav/Nav';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Home from './pages/Home/Home';
import AskQuestion from './components/AskQuestion/AskQuestion';
import NotFound from './pages/NotFound/NotFound';

function App() {
  return (
    <React.Fragment key={null}>
      <Nav />
      <Routes>
        <Route path="/" element={<Navigate replace to="/question" />} />
        <Route path="/question/*" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/ask" element={<AskQuestion />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </React.Fragment>
  );
}

export default App;
