import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from 'react-redux';
import {CREATE_OAUTH2} from "./actions/ActionType";
import {OAuth2} from "fetch-mw-oauth2/dist";
import {ConnectedRouter} from 'connected-react-router'
import history from "./history/history";
import configureStore from "./store";

export const createOAuth = (payload: OAuth2) => {
    return ({
        type: CREATE_OAUTH2,
        payload
    });
};

export const store = configureStore({});

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <App/>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
