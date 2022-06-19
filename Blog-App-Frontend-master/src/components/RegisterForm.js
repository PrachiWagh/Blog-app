import React, { useState } from 'react'
import history from './../history'
import avatar from './media/avatar.png'
import login from './css/login.css'
import axios from '../axios'

function RegisterForm(props) {
  const [state, setState] = useState({
    email: '',
    username: '',
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
      username: state.username,
      email: state.email,
      password: state.password,
    }

    axios.post('user/register', data).then((response) => {
      if (response.status == 200) {
        localStorage.setItem('loggedIn', true)
        localStorage.setItem('username', state.username)
        history.push("/home");
      } else {
        state.username = ''
        state.email = ''
        state.password = ''
        state.error_message = ''
        setLoading(false)
        setError(response.data.message)
        history.push('../login')
      }
    })
  }

  return (
    <div class="login_div">
      <form>
        <h2 class="headertekst">Registeration Form</h2>
        <div class="imgcontainer">
          <img src={avatar} alt="Avatar" class="avatar"></img>
        </div>
        <br></br>
        <div className="container">
          <label>Username</label>
          <input
            type="text"
            className="form-control"
            id="username"
            placeholder="Username"
            value={state.username}
            onChange={handleChange}
          />
        </div>
        <br></br>
        <div className="container">
          <label>E-Mail</label>
          <input
            type="email"
            className="form-control"
            id="email"
            placeholder="Email Address"
            value={state.email}
            onChange={handleChange}
          />
        </div>
        <br></br>
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
          Register
        </button>
      </form>
      <a class="authenticationSwitchLink" href="http://localhost:3000/login">
        Click here if you are already an user
      </a>
    </div>
  )
}

export default RegisterForm
