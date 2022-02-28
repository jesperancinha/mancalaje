import './App.sass';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {GameLogin} from "./game/game-login/GameLogin";
import './index.css';
import {connect} from "react-redux";
import {State} from "./reducers/reducerIndex";
import {GameList} from "./game/game-list/GameList";
import {GameStart} from "./game/game-start/GameStart";
import {Home} from "./home/Home";
import {GameRegister} from "./game/game-login/GameRegister";
import React from "react";

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={Home}/>
                <Route path="/login" element={GameLogin}/>
                <Route path="/gameList" element={GameList}/>
                <Route path="/gameStart/:id" element={GameStart}/>
                <Route path="/gameRegister" element={GameRegister}/>
            </Routes>
        </BrowserRouter>
    );
};

const mapStateToProps = (state: State) => {
    return state;
};
const AppConnected = connect(mapStateToProps)(App);

export {AppConnected, App}
