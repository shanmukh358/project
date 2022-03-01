import PropTypes from "prop-types";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { Link, Navigate, useParams } from "react-router-dom";
import {
  deleteFood,
  getFoodById,
  loadFood,
} from "../../food/actions/foodAction";

const FoodDetails = ({
  foods: {
    food: { foodPic, foodName, description, foodCost },
  },
  getFoodById,
  deleteFood,
  loadFood,
}) => {
  const { id } = useParams();
  useEffect(() => {
    getFoodById(id);
  }, [getFoodById, id]);

  // useEffect(() => {
  //   loadFood();
  // }, [loadFood]);

  return (
    <div>
      <div className="row food-details-container">
        <div class="col">
          <img src={foodPic} alt={foodName} class="img-thumbnail" />
        </div>
        <div class="col">
          <h3>{foodName}</h3>
          <h1>₹{foodCost}</h1>
          <div>{description}</div>
          <Link
            class="btn btn-danger"
            onClick={() => {
              deleteFood(id);
            }}
            to="/dashboard"
          >
            <i class="bi bi-trash"></i>
            Delete food
          </Link>
          <Link class="btn btn-danger" to={`/food/editfood/${id}`}>
            <i class="bi bi-trash"></i>
            Edit food
          </Link>
        </div>
      </div>
    </div>
  );
};

FoodDetails.propTypes = {
  getFoodById: PropTypes.func.isRequired,
  deleteFood: PropTypes.func.isRequired,
  loadFood: PropTypes.func.isRequired,
  foods: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  foods: state.foods,
  auth: state.auth,
});

const mapDispatchToProps = { getFoodById, deleteFood, loadFood };

export default connect(mapStateToProps, mapDispatchToProps)(FoodDetails);
