import React from 'react';
import './home/Home.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./home/Home";
import Game from "./game/Game";

class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={Home}/>
                    <Route path="/game" component={Game}/>
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;
