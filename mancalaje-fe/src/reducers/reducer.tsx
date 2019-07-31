import * as actionType from '../actions/ActionType';
import {MancalaAction} from '../actions/ActionType';
import {MancalaState} from "../entities/mancala-state";

const mancalaReducer = (state: MancalaState = {}, action: MancalaAction) => {
    if (!action) {
        return state;
    }
    switch (action.type) {
        case actionType.CREATE_OAUTH2:
            const newState = new MancalaState();
            newState.oauth = action.payload;
            return newState;
        default:
            return state;
    }
};

export {mancalaReducer};
