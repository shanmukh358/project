import React from "react";
import { Route, Routes } from "react-router-dom";
import Login from "../components/Login";
import Register from "../components/Register";

import Register3 from "../components/Register3";
export const AuthRouters = () => {
  return (
    <div>
      <Routes>
        <Route path="/register" element={<Register3></Register3>}></Route>
        <Route path="/login" element={<Login></Login>}></Route>
      </Routes>
    </div>
  );
};
