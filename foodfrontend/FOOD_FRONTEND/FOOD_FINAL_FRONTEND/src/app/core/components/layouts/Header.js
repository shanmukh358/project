import { Link } from "react-router-dom";
import { logout } from "../../../auth/action/authAction";
import React, { Fragment } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";

const Header = ({ auth: { user, isAuthenticated }, logout }) => {
  const authLinks = (
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      <li className="nav-item">
        <Link className="nav-link" to="/dashboard">
          Welcome {user && user.name}
        </Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/order-history">
          Order history
        </Link>
      </li>

      <li className="nav-item">
        <Link className="nav-link" to="/food/addFood">
          Add food item
        </Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/cart">
          <i className="bi bi-cart">Cart</i>
        </Link>
      </li>
      <li>
        <a onClick={logout} className="nav-link" href="/auth/login">
          <i className="fas fa-sign-out-alt hide-sm">Logout</i>
        </a>
      </li>
    </ul>
  );

  const guestLinks = (
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      <li className="nav-item">
        <Link className="nav-link" to="/auth/register">
          Register
        </Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/auth/login">
          Login
        </Link>
      </li>
    </ul>
  );

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <Link className="navbar-brand" to="/">
        Food Delivery
      </Link>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        {isAuthenticated ? authLinks : guestLinks}
      </div>
    </nav>
  );
};
Header.propTypes = {
  logout: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

const mapDispatchToProps = { logout };

export default connect(mapStateToProps, mapDispatchToProps)(Header);
