import {State} from "../reducers/reducerIndex";

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
            .catch(console.log)
            .finally(() => {
                if (props.oauth && !props.oauth.token.accessToken) {
                    props.history.push(LOGIN_PATH);
                }
            });
    }
}

export function makePostRequest<T>(urlString: string, props: T & State, transformData: any, messsageBody: any) {
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
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(console.log)
            .finally(() => {
                if (props.oauth && !props.oauth.token.accessToken) {
                    props.history.push(LOGIN_PATH);
                }
            });
    }
}

export function makePutRequest<T>(urlString: string, props: T & State, transformData: any, messsageBody: any) {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: messsageBody
        })
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(console.log)
            .finally(() => {
                if (props.oauth && !props.oauth.token.accessToken) {
                    props.history.push(LOGIN_PATH);
                }
            });
    }
}