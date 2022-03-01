import PropTypes from "prop-types";
import React, { useState } from "react";
import { connect } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { addToCart, removeFromCart } from "../../cart/action/cartAction";
import store from "../../../redux/store/index";

export const FoodCard = ({
  food,
  food: { id, foodName, foodPic, description },
  cart,
}) => {
  const [inCart, setInCart] = useState(
    cart.filter((item) => item.id === id).length > 0 ? true : false
  );
  return (
    <div className="food">
      <div className="card-img-container">
        <Link to={`/food/food-details/${id}`}>
          <img
            src={foodPic}
            className="card-img-top d-inline-block"
            alt={foodName}
            style={{ width: "20rem", height: "20rem" }}
          />
          <h3 style={{ color: "white" }}>{foodName}</h3>
        </Link>
      </div>
      {description}
      <div className="card-body">
        <h5 className="card-title">
          <button
            className={`btn btn-${inCart ? "danger" : "info"} mt-5`}
            onClick={(e) => {
              if (e.target.innerText === "Add to cart") {
                store.dispatch(addToCart(food));
              } else {
                store.dispatch(removeFromCart(id));
              }
              setInCart(!inCart);
            }}
          >
            {inCart ? "Remove from cart" : "Add to cart"}
          </button>
        </h5>
      </div>
    </div>
  );
};

FoodCard.propTypes = {
  food: PropTypes.object.isRequired,
  cart: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({
  foods: state.foods,
  cart: state.cart,
});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(FoodCard);
