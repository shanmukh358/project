// Register user
import {
  LOGIN_SUCCESS,
  LOGOUT,
  REGISTER_FAIL,
  REGISTER_SUCCESS,
  USER_LOADED,
} from "../../../redux/types/userTypes";
import api from "../../../utils/api";
import { setAlert } from "../../core/actions/alertAction";
import { loadFood } from "../../food/actions/foodAction";

export const register = (formData) => async (dispatch) => {
  try {
    const res = await api.post("/register", formData);

    // success
    // failure
    dispatch({ type: REGISTER_SUCCESS, payload: res.data });
    dispatch(setAlert("Successful Registration", "success"));
  } catch (err) {
    const errors = err.response.data.errors;
    if (errors) {
      errors.forEach((error) => dispatch(setAlert(error.msg, "danger")));
    }
    dispatch({ type: REGISTER_FAIL });
  }
};

// load User===> load the user inform
export const loadUser = (id) => async (dispatch) => {
  try {
    const res = await api.get(`/users/${id}`);
    dispatch({ type: USER_LOADED, payload: res.data });
  } catch (err) {}
};

export const login = (email, password) => async (dispatch) => {
  const body = JSON.stringify({ email, password });
  try {
    const res = await api.post("/authenticate", body);
    const id = res.data.user.id;
    dispatch({ type: LOGIN_SUCCESS, payload: res.data });
    dispatch(loadUser(id));
    dispatch(loadFood());
  } catch (err) {}
};

export const logout = () => async (dispatch) => {
  dispatch({ type: LOGOUT });
};
