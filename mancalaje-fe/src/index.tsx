import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from 'react-redux';
import {CREATE_OAUTH2} from "./actions/ActionType";
import {ConnectedRouter} from 'connected-react-router'
import history from "./history/history";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "./theme";
import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";
import {configureStore} from "./store";

export const createOAuth = (payload?: OAuth2) => {
    return ({
        payload,
        type: CREATE_OAUTH2
    });
};

export const store = configureStore({});

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <MuiThemeProvider theme={theme}>
                <App/>
            </MuiThemeProvider>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
