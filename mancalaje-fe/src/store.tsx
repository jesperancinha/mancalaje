import {applyMiddleware, compose, createStore} from 'redux'
import {routerMiddleware} from 'connected-react-router'
import {createRootReducer} from './reducers/reducerIndex'
import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";
import {CREATE_OAUTH2, PREV_STATE} from "./actions/ActionType";
import {createBrowserHistory} from "history";

const history = createBrowserHistory();

const configureStore = (preloadedState?: {}) => {
    const composeEnhancer: typeof compose = (window as any).__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
    return createStore(
        createRootReducer(history),
        preloadedState,
        composeEnhancer(
            applyMiddleware(
                routerMiddleware(history),
            ),
        ),
    )
};

const createOAuth = (payload?: OAuth2) => {
    return ({
        payload,
        type: CREATE_OAUTH2,
    });
};


const createPrevMessage = (prevAction?: string) => {
    return ({
        prevAction,
        type: PREV_STATE,
    });
};

const store = configureStore({});

export {configureStore, createOAuth, createPrevMessage, store};
