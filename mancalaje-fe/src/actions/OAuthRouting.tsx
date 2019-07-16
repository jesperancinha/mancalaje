import {State} from "../reducers/reducerIndex";
import {ErrorMessage} from "../entities/error-message";

const LOGIN_PATH = "/login";

const makeGetRequest = (urlString: string, state: State, props: State, transformData: any) => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: "GET",
        })
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(() => {
                logOut(props, state);
            })
    }
};


const makePostRequest = (urlString: string, state: State, props: State, transformData: any, messsageBody: any) => {
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
            .then((res: any) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => state.statusError = errorMessage.localizedMessage);
                    return null;
                }
                return res.json()
            })
            .then((data: any) => transformData(data))
            .catch(() => {
                logOut(props, state);
            })

    }
};

const makePutRequest = (urlString: string, state:
    State, props: State, transformData: any, messageBody: any, errorCatch?: any) => {
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
            .then((res: any) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => {
                        if (errorCatch) {
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

const makeDeleteRequest = (urlString: string, state: State, props: State, transformData: any = () => {
}, errorCatch?: any) => {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'DELETE'
        })
            .then((res: any) => {
                if (res.status === 409) {
                    res.json().then((errorMessage: ErrorMessage) => {
                        if (errorCatch) {
                            errorCatch(errorMessage.localizedMessage);
                        } else {
                            state.statusError = errorMessage.localizedMessage
                        }
                    });
                    return null;
                }
                return res.text()
            })
            .then((text => {
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

export {makeGetRequest, makePutRequest, makePostRequest, makeDeleteRequest, logOut}

