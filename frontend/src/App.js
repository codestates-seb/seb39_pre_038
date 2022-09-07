import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Spinner from './components/Spinner/Spinner';

const Nav = React.lazy(() => import('./components/Nav/Nav'));
const Home = React.lazy(() => import('./pages/Home/Home'));
const Login = React.lazy(() => import('./pages/Login/Login'));
const SignUp = React.lazy(() => import('./pages/SignUp/SignUp'));
const AskQuestion = React.lazy(() =>
  import('./components/AskQuestion/AskQuestion'),
);
const UpDateQuestion = React.lazy(() =>
  import('./components/UpDateQuestion/UpDateQuestion'),
);
const NotFound = React.lazy(() => import('./pages/NotFound/NotFound'));

function App() {
  return (
    <React.Suspense fallback={<Spinner />}>
      <Nav />
      <Routes>
        <Route path="/" element={<Navigate replace to="/question" />} />
        <Route path="/question/*" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/ask" element={<AskQuestion />} />
        <Route path="/update" element={<UpDateQuestion />} />
        <Route path="/404" element={<NotFound />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </React.Suspense>
  );
}

export default App;
