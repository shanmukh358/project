import PropTypes from "prop-types";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Navigate, useNavigate } from "react-router-dom";

export const PrivateRoute = ({
  component: Component,
  auth: { isAuthenticated, loading },
}) => {
  const Navigate = useNavigate();
  useEffect(() => {
    if (!isAuthenticated) {
      return Navigate("/auth/login");
    }
  });

  if (isAuthenticated) {
    return <Component></Component>;
  }
  return "";
};

PrivateRoute.propTypes = {
  auth: PropTypes.object.isRequired,
  component: PropTypes.element.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(PrivateRoute);
