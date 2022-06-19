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

import history from './../history'
import naviBar from './css/naviBar.css'

const NaviBar = function () {
  const [state, setState] = useState({
    search_text: '',
  })
  const [loading, setLoading] = useState(false)
  const [errorMessage, setError] = useState('')

  const handleChange = (e) => {
    const { id, value } = e.target
    setState((prevState) => ({
      ...prevState,
      [id]: value,
    }))
    console.log(e)
  }

  const handleSubmitClick = (e) => {
    setLoading(true)
    e.preventDefault()

    var axios = require('axios')
    var data = JSON.stringify({
      "search": state.search_text
    });

    var config = {
      method: 'get',
      url: 'http://localhost:8080/blogs/search',
      headers: {
        'Content-Type': 'application/json',
      },
      body: data,
    }

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data))
      })
      .catch(function (error) {
        console.log(error)
      })
  }

  return (
    <div class="container">
      <h1>
        <a href="#">Bootstrap Site</a>
      </h1>
      <div class="navbar navbar-default">
        <div class="navbar-inner">
          <div class="container">
            <ul class="nav navbar-nav">
              <li class="active">
                <a href="http://localhost:3000/home">
                  <h3 class="navFont">Home</h3>{' '}
                </a>
              </li>
              <li>
                <a href="http://localhost:3000/createBlog">
                  <h3 class="navFont">Create Blog</h3>{' '}
                </a>
              </li>
              <li>
                <Form className="d-flex">
                  <FormControl
                    type="search"
                    placeholder="Search"
                    className="me-2"
                    aria-label="Search"
                    onChange={handleChange}
                  />
                  <Button
                    id="searchButton"
                    variant="outline-success"
                    onClick={handleSubmitClick}
                  >
                    Search
                  </Button>
                </Form>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  )
}
export default NaviBar
