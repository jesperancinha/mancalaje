import {State} from "../reducers/reducerIndex";


export function makeRequest<T>(method: string, urlString: string, props: T & State, transformData: any) {
    if (!props.oauth) {
        props.history.push('/login');
    }
    props.oauth.fetch(urlString, {
        method: method
    })
        .then((res: any) => res.json())
        .then((data: any) => transformData(data))
        .catch(console.log)
        .finally(() => {
            if (!props.oauth.token.accessToken) {
                props.history.push('/login');
            }
        });
}