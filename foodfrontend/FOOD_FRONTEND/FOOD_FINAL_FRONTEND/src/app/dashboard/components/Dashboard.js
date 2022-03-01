import PropTypes from "prop-types";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import FoodCard from "../../food/components/FoodCard";
import { loadFood } from "../../food/actions/foodAction";
import { allFoods } from "../../assets/images/food-images/allFoods";
import { Row, Col } from "react-simple-flex-grid";
export const Dashboard = ({
  auth: { user },
  loadFood,
  foods: {
    food: { content },
  },
}) => {
  useEffect(() => {
    loadFood();
  }, [loadFood]);

  return (
    <div className="container" style={{ width: "110rem" }}>
      <h1 className="large text-primary">Dashboard</h1>
      <p className="lead">
        <i className="fas fa-user"></i> {user && user.name}
      </p>
      <div className="row main">
        <div className="row">
          <div className="col"></div>
          <div className="col"></div>
          <div className="col"></div>
          <div className="col"></div>
          <div className="col">
            <select className="form-select">
              <option default>Select Type</option>
              <option>Chinese</option>
              <option>Mexican</option>
              <option>Indian</option>
            </select>
          </div>
        </div>
        <div className="col-md-3 mt-3"></div>
        <div className="foods">
          {content &&
            content.map((f) => {
              return <FoodCard key={f.id} food={f}></FoodCard>;
            })}
        </div>

        <nav
          className="pagination"
          style={{
            display: "grid",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <ul className="pagination">
            <li className="page-item">
              <button className="page-link">Previous</button>
            </li>
            <li className="page-item">
              <button className="page-link">1</button>
            </li>
            <li className="page-item">
              <button className="page-link">2</button>
            </li>
            <li className="page-item">
              <button className="page-link">3</button>
            </li>
            <li className="page-item">
              <button className="page-link">Next</button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
};

Dashboard.propTypes = {
  auth: PropTypes.object.isRequired,
  loadFood: PropTypes.func.isRequired,
  foods: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  foods: state.foods,
});

const mapDispatchToProps = { loadFood };

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
