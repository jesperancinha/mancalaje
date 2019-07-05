import {combineReducers} from 'redux'
import {History} from 'history'
import {connectRouter, RouterState} from 'connected-react-router'
import mancalaReducer from "./reducer";
import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";

export default (history: History) => combineReducers({
    mancalaReducer: mancalaReducer,
    router: connectRouter(history)
});

export interface State {
    oauth?: OAuth2;
    router?: RouterState;
    history?: any;
    dispatch?: any;
}

