// ActionType.js

import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";

export const CREATE_OAUTH2 = "CREATE_OAUTH2";

export class MancalaAction {
    payload?: OAuth2;
    type?: string;
}
