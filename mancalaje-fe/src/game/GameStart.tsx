import React from 'react';
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import theme from "../theme";
import {Typography} from "@material-ui/core";

class GameStart extends React.Component {
    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <Typography variant="h1">Let the games begin!</Typography>
                <Typography component="h2" variant="h2">We're sorry, but MancalaJe isn't ready yet. Please try again later!</Typography>
            </MuiThemeProvider>
        );
    }
}

export default GameStart