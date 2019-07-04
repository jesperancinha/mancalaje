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
            .finally(logOut(props));

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
            .finally(logOut(props));

    }
}

export function makePutRequest<T>(urlString: string, props: T & State, transformData: any, messageBody: any) {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: messageBody
        })
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(console.log)
            .finally(logOut(props));

    }
}

export function makeDeleteRequest<T>(urlString: string, props: T & State, transformData: any = () => {
}) {
    if (!props.oauth) {
        props.history.push(LOGIN_PATH);
    } else {
        props.oauth.fetch(urlString, {
            method: 'DELETE'
        })
            .then((res: any) => res.json())
            .then((data: any) => transformData(data))
            .catch(console.log)
            .finally(logOut(props));
    }
}

function logOut<T>(props: T & State) {
    if (props.oauth && !props.oauth.token.accessToken) {
        return props.history.push(LOGIN_PATH);
    }
}
