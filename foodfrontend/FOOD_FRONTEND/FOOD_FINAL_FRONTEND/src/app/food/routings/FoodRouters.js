import React from "react";
import { Route, Routes, useParams } from "react-router-dom";
import AddFood from "../components/AddFood";
import EditFood from "../components/EditFood";
import FoodDetails from "../components/FoodDetails";

const FoodRouters = () => {
  return (
    <div>
      <Routes>
        <Route
          path="food-Details/:id"
          element={<FoodDetails></FoodDetails>}
        ></Route>
        <Route path="/addFood" element={<AddFood />}></Route>
        <Route path="/editfood/:id" element={<EditFood />}></Route>
      </Routes>
    </div>
  );
};

export default FoodRouters;
