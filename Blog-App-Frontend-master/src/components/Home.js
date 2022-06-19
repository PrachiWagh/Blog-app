import React, { Component, useState, useEffect } from 'react'
import home from "./css/home.css"
import NaviBar from './NaviBar'
import history from './../history'
import axios from '../axios'
import Loader from './Loader'

function Home(props) {
  const [loading, setLoader] = useState(true)
  const [blogs, setBlogs] = useState([])
  const fetchArticles = () => {
    axios
      .get('blogs/posts')
      .then(function (response) {
        console.log('response', response)
        console.log('response data :', response.data)
        if (response.status != 200) {
          history.push('../../../../../../login')
        }
        setBlogs(response.data)
        setLoader(false)
        return response.data
      })
      .catch(function (error) {
        console.log(error)
      })
  }
  useEffect(() => {
    fetchArticles()
  }, [])
  return loading ? (
    <Loader />
  ) : (
    <div>
      <NaviBar />
      <table class="styled-table">
        <thead>
          <tr>
            <th>Serial No.</th>
            <th>Title</th>
            <th>Body</th>
            <th>Created By</th>
            <th>Created On</th>
          </tr>
        </thead>
        <tbody>
          {blogs.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.body}</td>
              <td>{item.created_by}</td>
              <td>{item.creation_date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Home
