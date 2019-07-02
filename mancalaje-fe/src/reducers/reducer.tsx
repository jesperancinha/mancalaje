import * as actionType from '../actions/ActionType';
import {MancalaState} from "../types";


const mancalaReducer = (state: any = {}, action: any) => {
    if (!action) {
        return state;
    }
    switch (action.type) {
        case actionType.CREATE_OAUTH2:
            let newState = new MancalaState();
            newState.oauth = action.payload;
            return newState;
        default:
            return state
    }
};

export default mancalaReducer;