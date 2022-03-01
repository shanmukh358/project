import {
  FOOD_ERROR,
  GET_FOOD,
  GET_FOODS,
  ADD_FOOD,
  DELETE_FOOD,
  UPDATE_FOOD,
} from "../../../redux/types/foodTypes";
import api from "../../../utils/api";
import { setAlert } from "../../core/actions/alertAction";

export const loadFood = () => async (dispatch) => {
  try {
    const res = await api.get("/food");
    dispatch({ type: GET_FOODS, payload: res.data });
  } catch (err) {}
};

export const getFoodById = (id) => async (dispatch) => {
  try {
    console.log(id);
    const res = await api.get(`/food/${id}`);
    dispatch({ type: GET_FOOD, payload: res.data });
  } catch (err) {
    dispatch({
      type: FOOD_ERROR,
      payload: { msg: err.response, status: err.response },
    });
  }
};

export const addFood = (formData, navigate) => async (dispatch) => {
  try {
    console.log();
    const res = await api.post(`/food`, formData);
    dispatch({ type: ADD_FOOD, payload: res.data });
    dispatch(setAlert("Food Added Successfully", "success"));
    navigate("/dashboard");
  } catch (err) {
    dispatch({
      type: FOOD_ERROR,
      payload: { msg: err.response, status: err.response },
    });
  }
};

export const editFood = (formData, id, navigate) => async (dispatch) => {
  try {
    console.log(id);
    const res = await api.put(`/food/${id}`, formData);
    dispatch({ type: UPDATE_FOOD, payload: res.data });
    dispatch(setAlert("Food Edited Successfully", "success"));
    navigate("/dashboard");
  } catch (err) {
    dispatch({
      type: FOOD_ERROR,
      payload: { msg: err.response, status: err.response },
    });
  }
};

export const deleteFood = (id) => async (dispatch) => {
  try {
    console.log(id);
    const res = await api.delete(`/food/${id}`);
    dispatch({ type: DELETE_FOOD, payload: res.data });
    dispatch(setAlert("Food Deleted", "success"));
  } catch (err) {
    dispatch({
      type: FOOD_ERROR,
      payload: { msg: err.response, status: err.response },
    });
  }
};
