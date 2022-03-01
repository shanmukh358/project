import React, { Component } from "react";

export default class Footer extends Component {
  render() {
    return (
      <footer className="text-center fixed-bottom text-primary">
        Copyright &copy; {new Date().getFullYear()} All rights reserved
      </footer>
    );
  }
}
