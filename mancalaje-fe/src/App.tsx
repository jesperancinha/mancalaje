import React from 'react';
import './App.sass';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./home/Home";
import {GameLoginConnected} from "./game/game-login/GameLogin";
import './index.css';
import {connect} from "react-redux";
import {State} from "./reducers/reducerIndex";
import {GameListConnected} from "./game/game-list/GameList";
import {GameStartConnected} from "./game/game-start/GameStart";

const App: React.FC = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/login" component={GameLoginConnected}/>
                <Route path="/gameList" component={GameListConnected}/>
                <Route path="/gameStart/:id" component={GameStartConnected}/>
            </Switch>
        </Router>
    );
};

const mapStateToProps = (state: State) => {
    return state;
};
export default connect(mapStateToProps)(App);