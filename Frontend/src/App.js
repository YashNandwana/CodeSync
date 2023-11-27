import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'

import CodeEditor from './components/CodeEditor';
import LoginPage from './components/LoginPage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        {/* Use a route parameter to capture the room ID */}
        <Route path="/codeeditor/:roomId" element={<CodeEditor />} />
      </Routes>
    </Router>
  );
}

export default App;
