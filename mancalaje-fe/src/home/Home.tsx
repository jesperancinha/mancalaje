import React from 'react';
import logo from './logo.svg';
import './Home.css';
import {Link} from "react-router-dom";
import {Grid, Typography} from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";
import {GRID_SPACING, XS_COL_SPAN} from "../theme";

class Home extends React.Component {
    public render(): {} {
        return (
            <Grid
                container
                spacing={GRID_SPACING}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}>
                <Grid item xs={XS_COL_SPAN}>
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
                        <Typography component="p">
                            Demo version: Please note that this app will be updated with no warning on needed occasions on periods from 19h to 23h CET/CEST depending on current DST.
                            Your current game in progress will be removed, but your user will not. This means that you can login again, create rooms and play online.
                            In this current version you cannot change password. You will in future versions.
                            Remember that your user will be available for a maximum idle period of 5 hours and that there is a current limitation of 100 users and 50 rooms.
                            Expect bugs, conflicts and issues given that this is only an alfa version.
                        </Typography>
                    </AppBar>
                </Grid>
            </Grid>
        );
    }
}

export {Home};
