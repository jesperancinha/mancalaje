import * as actionType from '../actions/ActionType';
import {MancalaAction} from '../actions/ActionType';
import {MancalaState} from "../entities/mancala-state";

const mancalaReducer = (state: MancalaState = {}, action: MancalaAction) => {
    if (!action) {
        return state;
    }
    const newState = new MancalaState();
    switch (action.type) {
        case actionType.CREATE_OAUTH2:
            newState.oauth = action.payload;
            return newState;
        case actionType.PREV_STATE:
            newState.prevAction = action.prevAction;
            return newState;
    }
    return state;

};

export {mancalaReducer};
