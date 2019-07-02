import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./home/Home";
import Game from "./game/game/Game";
import GameList from "./game/game-list/GameList";
import GameStart from "./game/game-start/GameStart";
import './index.css';
import {connect} from "react-redux";

const App: React.FC = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/game" component={Game}/>
                <Route path="/gameList" component={GameList}/>
                <Route path="/gameStart/:id" component={GameStart}/>
            </Switch>
        </Router>
    );
};

const mapStateToProps = (state: any) => {
    return state;
};
export default connect(mapStateToProps)(App);