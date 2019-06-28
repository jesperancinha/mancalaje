import React from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./home/Home";
import Game from "./game/Game";
import GameStart from "./game/GameStart";

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/game" component={Game}/>
                <Route path="/gameStart" component={GameStart}/>
            </Switch>
        </BrowserRouter>
    );
};

export default App;
