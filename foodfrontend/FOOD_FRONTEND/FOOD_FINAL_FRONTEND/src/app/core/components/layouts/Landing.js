import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Link } from "react-router-dom";

const Landing = ({ auth: { isAuthenticated } }) => {
  return (
    <div className="landing">
      <div className="dark-overlay landing-inner text-light">
        <div className="container">
          <div className="row">
            <div className="col-md-12 text-center mt-5">
              <h1 className="display-3 mb-4 mt-5">Food Delivery</h1>

              {!isAuthenticated && (
                <>
                  <p className="lead">Create your Food Delivery account now!</p>
                  <hr />
                  <Link
                    to="/auth/register"
                    className="btn btn-lg btn-info me-2"
                  >
                    Sign Up
                  </Link>
                  <Link to="/auth/login" className="btn btn-lg btn-light">
                    Login
                  </Link>
                </>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

Landing.propTypes = {
  auth: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(Landing);
