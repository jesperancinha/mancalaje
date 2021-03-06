import {State} from "../reducers/reducerIndex";
import {ErrorMessage} from "../entities/error-message";
import {CONFLICT} from "http-status-codes"

const LOGIN_PATH = "/login";

const makeGetRequest = <T extends {}>(urlString: string, state: State, props: State,
                                      transformData: (t: T) => void): void => {
    if (props.history) {
        if (!props.oauth) {
            props.history.push(LOGIN_PATH);
        } else {
            props.oauth.fetch(buildUrl(urlString), {
                method: "GET",
            })
                .then((res: Response) => res.json())
                .then((data: T) => transformData(data))
                .catch(() => {
                    logOut(props, state);
                })
        }
    }
};


const extractedFetch = (props: State): (urlString: string, config: RequestInit) => Promise<Response> => {
    if (props.oauth) {
        return (urlString: string, config: {}) => props.oauth ?
            props.oauth.fetch(urlString, config) :
            new Promise<Response>(() => {
            });
    } else {
        return (urlString: string, config: {}) => fetch(urlString, config);
    }
};

const makePostRequest = <T extends {}>(
    urlString: string, state: State, props: State,
    transformData: (t: T) => void, messsageBody: string,
    errorCatch?: (t: string) => void): void => {
    if (props.history) {
        extractedFetch(props)(buildUrl(urlString), {
            body: messsageBody,
            headers: {
                'Content-Type': 'application/json',
            },
            method: "POST",
        }).then((res: Response) => {
            if (res.status === CONFLICT) {
                res.json()
                    .then((errorMessage: ErrorMessage) => {
                        if (errorCatch && errorMessage.localizedMessage) {
                            errorCatch(errorMessage.localizedMessage);
                        } else {
                            state.statusError = errorMessage.localizedMessage
                        }
                    })
                    .catch(error => errorCatch && errorCatch(error));
                return null;
            }
            return res.json()
        }).then((data: T) => {
            if (data) {
                transformData(data)
            }
        }).catch(() => {
            logOut(props, state);
        })

    }

};

const makePutRequest = <T extends {}>(urlString: string, state:
    State, props: State, transformData: (t: T) => void = () => {
}, messageBody?: string, errorCatch?: (t: string) => void): void => {
    if (props.history) {
        if (!props.oauth) {
            props.history.push(LOGIN_PATH);
        } else {
            const config: RequestInit = {
                method: 'PUT',
            };
            if (messageBody) {
                config.body = messageBody;
                config.headers = {
                    'Content-Type': 'application/json',
                };
            }
            props.oauth.fetch(buildUrl(urlString), config)
                .then((res: Response) => {
                    if (res.status === CONFLICT) {
                        res.json()
                            .then((errorMessage: ErrorMessage) => {
                                if (errorCatch && errorMessage.localizedMessage) {
                                    errorCatch(errorMessage.localizedMessage);
                                } else {
                                    state.statusError = errorMessage.localizedMessage
                                }
                            })
                            .catch(error => errorCatch && errorCatch(error));
                        return null;
                    }
                    return res.json()
                })
                .then((data: T) => {
                    if (data) {
                        transformData(data)
                    }
                })
                .catch(() => {
                    logOut(props, state);
                })

        }
    }
};
const makeDeleteRequest = <T extends {}>
(urlString: string, state: State, props: State,
 transformData: (t: string) => void = () => {
 },
 errorCatch?: (errorMessage: string) => void): void => {
    if (props.history) {

        if (!props.oauth) {
            props.history.push(LOGIN_PATH);
        } else {
            props.oauth.fetch(buildUrl(urlString), {
                method: 'DELETE',
            })
                .then((res: Response) => {
                    if (res.status === CONFLICT) {
                        res.json()
                            .then((errorMessage: ErrorMessage) => {
                                if (errorCatch && errorMessage.localizedMessage) {
                                    errorCatch(errorMessage.localizedMessage);
                                } else {
                                    state.statusError = errorMessage.localizedMessage
                                }
                            });
                        return '';
                    }
                    return res.text()
                })
                .then(((text) => {
                    if (text) {
                        return text;
                    } else {
                        return "{}";
                    }
                }))
                .then((data: string) => transformData(data))
                .catch((error) => {
                    console.log(error);
                    logOut(props, state);
                })
        }
    }
};

const home = (props: State): void => {
    if (props.history) {
        props.history.push(LOGIN_PATH);
    }
};
const logOut = (props: State, state: State): void => {
    if (props.history) {

        if (state.refreshers) {
            state.refreshers.map(clearInterval);
        }
        makeDeleteRequest("/open/oauth/token", state, props, () => home(props));
    }
};

function buildUrl(urlString: string) {
    return "/api/mancala" + urlString;
}

export {
    makeGetRequest, makePutRequest, makePostRequest,
    makeDeleteRequest, logOut, home,
}
