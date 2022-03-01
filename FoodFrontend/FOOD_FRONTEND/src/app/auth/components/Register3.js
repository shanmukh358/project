import PropTypes from "prop-types";
import React, { useState } from "react";
import { connect } from "react-redux";
import { Navigate } from "react-router-dom";
import { register } from "../action/authAction";

const Register3 = ({ isAuthenticated, register }) => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    city: "",
    houseno: "",
    street: "",
    zip: "",
    state: "",
  });
  const [error, setError] = useState({});
  const { name, email, password, state, houseno, street, zip, city } = formData;
  const onChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const onSubmit = (e) => {
    e.preventDefault();
    const address = { state, houseno, street, zip, city };
    register({ name, email, password, address });
  };

  if (isAuthenticated) {
    return <Navigate to="/dashboard"></Navigate>;
  }
  return (
    <div className="register">
      <div className="container">
        <div className="row">
          <div className="col-md-8 m-auto">
            <h1 className="display-4 text-center">Sign Up</h1>
            <p className="lead text-center">Create your account</p>
            <form onSubmit={onSubmit}>
              <div className="form-group">
                <input
                  type="email"
                  className="form-control form-control-lg"
                  placeholder="Email Address"
                  name="email"
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="Name"
                  name="name"
                  required
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="password"
                  className="form-control form-control-lg"
                  placeholder="Password"
                  name="password"
                  onChange={onChange}
                />
              </div>
              <div>{error.password}</div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="State"
                  name="state"
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="City"
                  name="city"
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="Street"
                  name="street"
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="House Number"
                  name="houseno"
                  onChange={onChange}
                />
              </div>
              <div className="form-group">
                <input
                  type="text"
                  className="form-control form-control-lg"
                  placeholder="Zip"
                  name="zip"
                  onChange={onChange}
                />
              </div>
              <input type="submit" className="btn btn-info btn-block mt-4" />
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

Register3.propTypes = {
  register: PropTypes.func.isRequired,
  isAuthenticated: PropTypes.bool,
};

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated,
});

export default connect(mapStateToProps, { register })(Register3);
