import logo from './logo.svg'
import './App.css'
import history from './history'
import { Router, Switch, Route, Redirect, HashRouter } from 'react-router-dom'

import LoginForm from './components/LoginForm'
import RegisterForm from './components/RegisterForm'
import CreateBlog from './components/CreateBlog'
import Error404 from './components/error404'
import Home from './components/Home'

function App() {
  return (
    <div>
      <Router history={history}>
        <Switch basename="/">
          <Route path="/login">
            <LoginForm />
          </Route>
          <Route path="/register">
            <RegisterForm />
          </Route>
          <Route path="/home">
            <Home />
          </Route>
          <Route path="/createBlog">
            <CreateBlog />
          </Route>
          <Route component={Error404} />
        </Switch>
      </Router>
    </div>
  )
}

export default App
