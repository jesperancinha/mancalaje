import {State} from "../reducers/reducerIndex";
import {ErrorMessage} from "../entities/error-message";

const LOGIN_PATH = "/login";

const makeGetRequest = <T extends {}>(urlString: string, state: State, props: State, transformData: (t: T) => void): void => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: "GET",
        })
            .then((res: Response) => res.json())
            .then((data: T) => transformData(data))
            .catch(() => {
                logOut(props, state);
            })
    }
};


const makePostRequest = <T extends {}>(urlString: string, state: State, props: State, transformData: (t: T) => void, messsageBody: string): void => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: messsageBody,
        })
            .then((res: Response) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => state.statusError = errorMessage.localizedMessage);
                    return null;
                }
                return res.json()
            })
            .then((data: T) => transformData(data))
            .catch(() => {
                logOut(props, state);
            })

    }
};

const makePutRequest = <T extends {}>(urlString: string, state:
    State, props: State, transformData: (t: T) => void = () => {
}, messageBody?: string, errorCatch?: (t: string) => void): void => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        const config: RequestInit = {
            method: 'PUT',
        };
        if (messageBody) {
            config.body = messageBody;
            config.headers = {
                'Content-Type': 'application/json'
            };
        }
        props.oauth.fetch(urlString, config)
            .then((res: Response) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => {
                        if (errorCatch && errorMessage.localizedMessage) {
                            errorCatch(errorMessage.localizedMessage);
                        } else {
                            state.statusError = errorMessage.localizedMessage
                        }
                    });
                    return null;
                }
                return res.json()
            })
            .then((data: any) => {
                if (data) {
                    transformData(data)
                }
            })
            .catch(() => {
                logOut(props, state);
            })

    }
};

const makeDeleteRequest = <T extends {}>
(urlString: string, state: State, props: State,
 transformData: (t: T) =>
     void = () => {
 }, errorCatch?: any): void => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'DELETE'
        })
            .then((res: Response) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => {
                        if (errorCatch) {
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
                    return {};
                }
            }))
            .then((data: any) => transformData(data))
            .catch((error) => {
                console.log(error);
                logOut(props, state);
            })
    }
};

const logOut = (props: State, state: State) => {
    if (state.refreshers) {
        state.refreshers.map(clearInterval);
    }
    return props.history.push(LOGIN_PATH);
};

const home = (props: State) => {
    props.history.push(LOGIN_PATH);
};

export {makeGetRequest, makePutRequest, makePostRequest, makeDeleteRequest, logOut, home}

