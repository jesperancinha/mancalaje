import React from 'react';
import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./home/Home";
import Game from "./game/game/Game";
import GameList from "./game/game-list/GameList";
import GameStart from "./game/game-start/GameStart";
import './index.css';

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/game" component={Game}/>
                <Route path="/gameList" component={GameList}/>
                <Route path="/gameStart" component={GameStart}/>
            </Switch>
        </BrowserRouter>
    );
};

export default App;
