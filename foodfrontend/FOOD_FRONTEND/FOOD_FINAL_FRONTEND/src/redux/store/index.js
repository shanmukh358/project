import thunk from "redux-thunk";
import { createStore, applyMiddleware } from "redux";
import combineReducers from "../reducers";
import { composeWithDevTools } from "redux-devtools-extension";
import setAuthToken from "../../utils/setAuthToken";

const initialState = {};
// default state for ur application.

// middleware [thunk]

const middleware = [thunk];

const store = createStore(
  combineReducers,
  initialState,
  // middleware and redux devtools(for dev env)
  composeWithDevTools(applyMiddleware(...middleware)) //middleware spec
);

// to get the current state

let currentState = store.getState();

// subscribe listener
// getting the token (for private end points)

store.subscribe(() => {
  let previousState = currentState;
  // old /previous state
  currentState = store.getState();
  // recent one

  // can we compare the date from two states?
  if (previousState.auth.token !== currentState.auth.token) {
    const token = currentState.auth.token;
    setAuthToken(token);
  }
  // based on that can we take a call to update the token?
});

export default store;
