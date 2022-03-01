import PropTypes from "prop-types";
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { editFood } from "../actions/foodAction";

const EditFood = ({ editFood, foods }) => {
  const [formData, setFormData] = useState({
    foodName: "",
    foodCost: "",
    foodType: "",
    description: "",
    foodPic: "",
  });

  const { id } = useParams();
  console.log(id);

  const navigate = useNavigate();
  const [error, setError] = useState({});
  const { foodName, foodCost, foodType, description, foodPic } = formData;

  const onChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const onSubmit = (e) => {
    e.preventDefault();
    editFood(
      { foodName, foodCost, foodType, description, foodPic },
      id,
      navigate
    );
  };

  return (
    <div class="row" style={{ marginTop: "50px" }}>
      <div class="col-3"></div>
      <div class="col">
        <h3>Edit food details</h3>
        <form onSubmit={onSubmit}>
          <div class="mb-3">
            <label class="form-label">Food name</label>
            <input
              type="text"
              class="form-control"
              id="foodName"
              name="foodName"
              onChange={onChange}
              aria-describedby="foodNameHelp"
            />
          </div>
          <div class="mb-3">
            <label class="form-label">Food cost</label>
            <div class="input-group mb-3">
              <span class="input-group-text">₹</span>
              <input
                type="number"
                class="form-control"
                id="foodCost"
                name="foodCost"
                onChange={onChange}
                aria-label="Amount (to the nearest dollar)"
              />
              <span class="input-group-text">.00</span>
            </div>
          </div>
          <div class="mb-3">
            <label class="form-label">Food description</label>
            <input
              class="form-control"
              style={{ maxHeight: "150px", minHeight: "150px" }}
              id="foodDesc"
              name="description"
              aria-describedby="foodDescHelp"
              onChange={onChange}
            ></input>
          </div>
          <div class="mb-3">
            <label class="form-label">Food picture URL</label>
            <input
              type="web"
              class="form-control"
              id="foodPic"
              name="foodPic"
              aria-describedby="foodPicHelp"
              onChange={onChange}
            />
          </div>
          <div class="mb-3">
            <label class="form-label">Food type</label>
            <select
              class="form-select"
              name="foodType"
              id="foodType"
              onChange={onChange}
            >
              <option default value="SelectType">
                SelectType
              </option>
              <option>INDIAN</option>
              <option>CHINESE</option>
              <option>MEXICAN</option>
            </select>
          </div>

          <button type="submit" class="btn btn-primary">
            Save new food details
          </button>
        </form>
      </div>
      <div class="col-3"></div>
    </div>
  );
};

EditFood.propTypes = {
  editFood: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  foods: state.foods,
});

const mapDispatchToProps = { editFood };

export default connect(mapStateToProps, mapDispatchToProps)(EditFood);
