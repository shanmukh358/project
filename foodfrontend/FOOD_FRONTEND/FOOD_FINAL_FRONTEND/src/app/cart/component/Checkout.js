import PropTypes from "prop-types";
import React, { useEffect } from "react";
import { connect } from "react-redux";
import { fetchFromCart } from "../action/cartAction";
export const Checkout = ({
  auth: { user, isAuthenticated },
  fetchFromCart,
}) => {
  const { state, houseno, street, zip, city } = user.address;
  useEffect(() => {
    if (isAuthenticated) {
      //clear cart data on the local storage when user places/checkouts the order
      localStorage.removeItem("cart");
      //update the cart
      fetchFromCart();
    }
  }, [fetchFromCart, isAuthenticated]);
  return (
    <div className="mt-5 green-box me-5 ms-5 mb-5">
      <div className="display-6">
        Your order has been placed
        <br /> <br />
        <h2>Happy Eating :)</h2>
      </div>

      <div className="mt-5 mb-5">
        <h3>Delivery address:</h3>
        <hr />
        <h4 className="lead mb-5">
          {user && `${houseno} ${street} ${zip} ${city}`}
        </h4>
      </div>
    </div>
  );
};

Checkout.propTypes = {
  auth: PropTypes.object.isRequired,
  fetchFromCart: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({ auth: state.auth });

const mapDispatchToProps = { fetchFromCart };

export default connect(mapStateToProps, mapDispatchToProps)(Checkout);
