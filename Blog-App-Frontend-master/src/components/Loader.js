import React, { useState, useEffect } from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
import "./css/loader.css";

const Loader = function () {
  return (
    <div className="loader">
      <p>Loading</p>
      <ul class="unlistLoader">
        <li class="loaderList"></li>
        <li class="loaderList"></li>
        <li class="loaderList"></li>
        <li class="loaderList"></li>
        <li class="loaderList"></li>
        <li class="loaderList"></li>
        <li class="loaderList"></li>
      </ul>
    </div>
  );
};
export default Loader;
