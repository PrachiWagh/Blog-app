import React, { useState } from 'react'
import {
  Navbar,
  Nav,
  Container,
  NavDropdown,
  Button,
  Form,
  FormGroup,
  FormControl,
} from 'react-bootstrap'

import NaviBar from './NaviBar'
import naviBar from './css/naviBar.css'
import history from './../history'
import avatar from './media/avatar.png'
import login from './css/login.css'
import axios from '../axios'
import createBlog from "./css/createBlog.css"

const CreateBlog = function () {
  const [state, setState] = useState({
    title: '',
    body: '',
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
      title: state.title,
      body: state.body,
      username: localStorage.getItem('username'),
    }

    axios.post('blogs/createpost', data).then((response) => {
      if (response.status == 200) {
        history.push('/home')
      } else {
        state.usernameOrEmail = ''
        state.password = ''
        state.error_message = ''
        setLoading(false)
        setError(response.data.message)
        history.push('../login')
      }
    })
  }
  return (
    <div>
      <NaviBar />
      <div class="createBlogDiv">
        <form>
          <h2 class="headertekst">Create Blog</h2>
          <br></br>
          <div className="container">
            <label>Title</label>
            <input
              type="text"
              className="form-control"
              id="title"
              placeholder="Title"
              value={state.title}
              onChange={handleChange}
            />
          </div>
          <br></br>
          <div className="container">
            <label>Body</label>
            <input
              type="text"
              className="form-control"
              id="body"
              placeholder="body"
              value={state.body}
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
          <br></br>
          <br></br>
          <button type="submit" className="button" onClick={handleSubmitClick}>
            Create Blog
          </button>
        </form>
      </div>
    </div>
  )
}
export default CreateBlog
