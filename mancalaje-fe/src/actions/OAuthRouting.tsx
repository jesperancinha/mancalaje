import {State} from "../reducers/reducerIndex";
import {ErrorMessage} from "../types";

const LOGIN_PATH = '/login';

export function makeGetRequest<T>(urlString: string, props: T & State, transformData: any) {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'GET'
        })
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(() => {
                logOut(props);
            })
    }
}

export function makePostRequest<T>(urlString: string, state: T & State, props: T & State, transformData: any, messsageBody: any) {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: messsageBody
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
                logOut(props);
            })

    }
}

export function makePutRequest<T>(urlString: string, state: T & State, props: T & State, transformData: any, messageBody: any, errorCatch?: any) {
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
                logOut(props);
            })

    }
}

export function makeDeleteRequest<T>(urlString: string, state: T & State, props: T & State, transformData: any = () => {
}, errorCatch?: any) {
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
                logOut(props);
            })
    }
}

export function logOut<T>(props: T & State) {
    return props.history.push(LOGIN_PATH);
}
