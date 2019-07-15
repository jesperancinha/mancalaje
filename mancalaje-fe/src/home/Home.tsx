import React from 'react';
import logo from './logo.svg';
import './Home.css';
import {Link} from "react-router-dom";
import {Grid, Typography} from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";

class Home extends React.Component {
    render() {
        return (
            <Grid
                container
                spacing={0}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}>
                <Grid item xs={12}>
                    <AppBar title={"Home Mancala Je"} position="relative">
                        <Typography variant="h1" align={"center"}>Welcome to MancalaJe</Typography>
                    </AppBar>
                    <AppBar title={"Home Mancala Je"} position="relative">
                        <Typography component="p" align={"center"}>click on the giant reactjs logo to start</Typography>
                        <Link to={`/login`}>
                            <img src={logo} className="App-logo" alt="logo"/>
                        </Link>
                        <Box>
                            <a
                                className="App-link"
                                href="https://reactjs.org"
                                target="_blank"
                                rel="noopener noreferrer"
                            >
                                Learn React
                            </a>
                        </Box>
                        <Typography component="p">coming soon... this is just a test app!</Typography>
                    </AppBar>
                </Grid>
            </Grid>
        );
    }
}

export {Home};