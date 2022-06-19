import React, { useState } from 'react'
import history from './../history'
import avatar from './media/avatar.png'
import login from './css/login.css'
import axios from '../axios'
import { Redirect } from 'react-router-dom'

function LoginForm(props) {
  const [state, setState] = useState({
    usernameOrEmail: '',
    password: '',
    error_message: '',
  })
  const [loading, setLoading] = useState(false)
  const [errorMessage, setError] = useState('')

  const handleChange = (e) => {
    const { id, value } = e.target
    setState((prevState) => ({
      ...prevState,
      [id]: value,
    }))
  }

  const handleSubmitClick = (e) => {
    setLoading(true)
    e.preventDefault()

    const data = {
      usernameOrEmail: state.usernameOrEmail,
      password: state.password,
    }

    axios.post('user/login', data).then((response) => {
      if (response.status == 200) {
        localStorage.setItem('loggedIn', true)
        localStorage.setItem('username', state.usernameOrEmail)
        history.push('/home')
      } else {
        state.usernameOrEmail = ''
        state.password = ''
        state.error_message = ''
        setLoading(false)
        setError(response.data.message)
        history.push('../login')
        document.location.reload()
      }
    })
  }

  return (
    <div class="login_div">
      <form>
        <h2 class="headertekst">Login Form</h2>
        <div class="imgcontainer">
          <img src={avatar} alt="Avatar" class="avatar"></img>
        </div>
        <br></br>
        <div className="container">
          <label>Username/E-Mail</label>
          <input
            type="text"
            className="form-control"
            id="usernameOrEmail"
            placeholder="Username/E-Mail"
            value={state.usernameOrEmail}
            onChange={handleChange}
          />
        </div>

        <div className="container">
          <label>Password</label>
          <input
            type="password"
            className="form-control"
            id="password"
            placeholder="Password"
            value={state.password}
            onChange={handleChange}
          />
        </div>
        {loading && <span class="spanLoader" />}
        {errorMessage.length > 0 && (
          <div
            id="errorMesssage"
            style={{
              textAlign: 'center',
              backgroundColor: '#f95a5a',
              margin: '10px 25px',
              padding: '5px 0',
            }}
          >
            {errorMessage}
          </div>
        )}
        <button type="submit" className="button" onClick={handleSubmitClick}>
          Login
        </button>
      </form>
      <a class="authenticationSwitchLink" href="http://localhost:3000/register">
        Click here to SignUp
      </a>
    </div>
  )
}

export default LoginForm
