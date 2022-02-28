import React from 'react';
import './App.sass';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {GameLogin} from "./game/game-login/GameLogin";
import './index.css';
import {connect} from "react-redux";
import {State} from "./reducers/reducerIndex";
import {GameList} from "./game/game-list/GameList";
import {GameStart} from "./game/game-start/GameStart";
import {Home} from "./home/Home";
import {GameRegister} from "./game/game-login/GameRegister";

const App: React.FC = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/login" component={GameLogin}/>
                <Route path="/gameList" component={GameList}/>
                <Route path="/gameStart/:id" component={GameStart}/>
                <Route path="/gameRegister" component={GameRegister}/>
            </Switch>
        </Router>
    );
};

const mapStateToProps = (state: State) => {
    return state;
};
const AppConnected = connect(mapStateToProps)(App);

export {AppConnected, App}
