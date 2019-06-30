import React from 'react';
import logo from './logo.svg';
import './Home.css';
import {Link} from "react-router-dom";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "../theme";
import {Typography} from "@material-ui/core";

class Home extends React.Component {
    render() {
        return (
            <div className="App">
                <MuiThemeProvider theme={theme}>
                    <header className="App-header">
                        <Typography variant="h1">Welcome to MancalaJe</Typography>
                        <Typography component="p">click on the giant reactjs logo to start</Typography>
                        <Link to={`/game`}>
                            <img src={logo} className="App-logo" alt="logo"/>
                        </Link>
                        <a
                            className="App-link"
                            href="https://reactjs.org"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            Learn React
                        </a>
                        <Typography component="p">coming soon... this is just a test app!</Typography>
                    </header>
                </MuiThemeProvider>
            </div>
        );
    }
}

export default Home;