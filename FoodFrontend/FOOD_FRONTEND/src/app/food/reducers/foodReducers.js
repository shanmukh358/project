import {
  GET_FOODS,
  GET_FOOD,
  FOOD_ERROR,
  DELETE_FOOD,
  ADD_FOOD,
} from "../../../redux/types/foodTypes";

const initialState = {
  food: null,
  loading: true,
};

export default (state = initialState, action) => {
  const { type, payload } = action;

  switch (type) {
    case GET_FOODS:
      return {
        ...state,
        loading: false,
        food: payload,
      };
    case GET_FOOD:
      return {
        ...state,
        loading: false,
        food: payload,
      };
    case DELETE_FOOD:
      return { ...state, ...payload, loading: false };

    case FOOD_ERROR:
      return { ...state, loading: false };

    case ADD_FOOD:
      return {
        ...state,
        ...payload,
        loading: false,
      };
    default:
      return state;
  }
};
