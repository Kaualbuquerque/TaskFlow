/* Router */
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

/* Pages */
import Home from "./components/pages/Home"
import Lists from "./components/pages/Lists"
import Tasks from "./components/pages/Tasks"


function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/lists" element={<Lists />}></Route>
        <Route path="/tasks" element={<Tasks />}></Route>
      </Routes>
    </Router>
  )
}

export default App
