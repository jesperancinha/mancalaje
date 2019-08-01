// ActionType.js

import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";

export const PREV_STATE = "PREV_STAT";

export const CREATE_OAUTH2 = "CREATE_OAUTH2";

export class MancalaAction {
    public payload?: OAuth2;
    public type?: string;
    public prevAction?: string;
}
