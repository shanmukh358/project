import React from "react";
import { Route, Routes } from "react-router-dom";
import Checkout from "./component/Checkout";
import ViewCart from "./component/ViewCart";

const CartRouter = () => {
  return (
    <Routes>
      <Route path="/" element={<ViewCart />} />
      {/* Making checkout option available to only customers */}
      <Route path="/checkout" element={<Checkout />} />
    </Routes>
  );
};

export default CartRouter;
