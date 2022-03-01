import {
  LOGIN_SUCCESS,
  REGISTER_FAIL,
  REGISTER_SUCCESS,
  USER_LOADED,
  LOGOUT,
} from "../../../redux/types/userTypes";

const initialState = {
  user: null,
  isAuthenticated: false, // boolean value
  loading: true,
  token: localStorage.getItem("token"), // after successfull login we will set token into localStorage.
};

export default (state = initialState, action) => {
  const { type, payload } = action;

  switch (type) {
    case USER_LOADED:
      return {
        ...state,
        isAuthenticated: true,
        loading: false,
        user: payload,
      };
    case REGISTER_SUCCESS:
      return { ...state, ...payload, isAuthenticated: true, loading: false };
    case LOGIN_SUCCESS:
      return { ...state, ...payload, isAuthenticated: true, loading: false }; // it will return this json object to store .& update the store content as per the new payload.

    case REGISTER_FAIL:
      return { ...state, token: null, isAuthenticated: false, loading: false };

    case LOGOUT:
      return {
        ...state,
        user: null,
        isAuthenticated: false, // boolean value
        loading: false,
        token: null, // after successfull login we will set token into localStorage.
      };
    default:
      return state;
  }
};
