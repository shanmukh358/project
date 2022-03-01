import { REMOVE_ALERT, SET_ALERT } from "../../../redux/types/alertTypes";

const initialState = [];

export default (state = initialState, action) => {
  const { type, payload } = action;
  switch (type) {
    case SET_ALERT:
      return [...state, payload];
    case REMOVE_ALERT:
      // payload will bring the id===> based on id(unique in nature)
      return state.filter((alert) => alert.id !== payload);

    default:
      return state;
  }
};
