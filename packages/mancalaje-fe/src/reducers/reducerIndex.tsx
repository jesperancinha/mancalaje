import {combineReducers, Dispatch} from 'redux'
import {History} from 'history'
import {connectRouter, RouterState} from 'connected-react-router'
import {mancalaReducer} from "./reducer";
import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";

export const createRootReducer = (history: History) => combineReducers({
    mancalaReducer: mancalaReducer,
    router: connectRouter(history),
});

export interface State {
    oauth?: OAuth2;
    router?: RouterState;
    history?: History;
    dispatch?: Dispatch;
    statusError?: string;
    refreshers: number[];
    prevAction?: string;

}

export interface Clickable {
    onClick?: () => void;
    children: {};

}
