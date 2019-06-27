import React from 'react';
import logo from './logo.svg';
import './Home.css';
import {Link} from "react-router-dom";

class Home extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1>Welcome do MancalaJe</h1>
                    <p>click on the giant reactjs logo to start</p>
                    <Link to={`/game`} activeClassName="active">
                        <img src={logo} className="App-logo" alt="logo"/>
                    </Link>
                    <a
                        className="App-link"
                        href="https://reactjs.org"
                        target="/game"
                        rel="noopener noreferrer"
                    >
                        Learn React
                    </a>
                    <p>coming soon...  this is just a test app!</p>
                </header>
            </div>
        );
    }
}

export default Home;