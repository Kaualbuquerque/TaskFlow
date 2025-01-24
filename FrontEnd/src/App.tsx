/* Router */
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

/* Pages */
import Home from "./components/pages/Home"
import Lists from "./components/pages/Lists"
import Tasks from "./components/pages/Tasks"
import Register from './components/pages/Register'
import Login from './components/pages/Login'


function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/lists" element={<Lists />}></Route>
        <Route path="/tasks" element={<Tasks />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/login" element={<Login />}></Route>
      </Routes>
    </Router>
  )
}

export default App
