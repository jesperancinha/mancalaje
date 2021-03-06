import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {AppConnected} from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from 'react-redux';
import {ConnectedRouter} from 'connected-react-router'
import {history} from "./history/history";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "./theme";
import {store} from "./store";

ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <MuiThemeProvider theme={theme}>
                <AppConnected/>
            </MuiThemeProvider>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
