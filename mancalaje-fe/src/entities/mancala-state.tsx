import OAuth2 from "fetch-mw-oauth2/dist/fetch-wrapper";

export class MancalaState {
    public oauth?: OAuth2;
    public prevAction? : string;
}
