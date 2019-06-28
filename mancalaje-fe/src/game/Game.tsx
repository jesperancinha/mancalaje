import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import theme from "../theme";
import Typography from "@material-ui/core/Typography";

class Game extends Component {
    constructor({props}: { props: any }) {
        super(props);
        this.state = {
            username: '',
            password: ''
        }
    }

    render() {
        return (
            <Grid
                container
                spacing={0}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}
            >
                <Grid item xs={12}>
                    <AppBar title="Title" position="relative">
                        <Typography align="center" component="h1" variant="h1">MancalaJe</Typography>
                    </AppBar>
                </Grid>
                <Grid item xs={12}>
                    <div style={control}></div>
                </Grid>
                <Grid item xs={12}>
                    <MuiThemeProvider theme={theme}>
                        <AppBar title="Login" position="relative" style={appBar}>
                            <Typography variant="h3" align={"center"}>Please login to start playing!</Typography>
                            <TextField
                                style={control}
                                label={'Username'}
                                onChange={(newValue) => this.setState({username: newValue})}/>
                            <br/>
                            <TextField
                                style={control}
                                label={'Password'}
                                type="password"
                                onChange={(newValue) => this.setState({password: newValue})}/>
                            <br/>
                            <Button
                                style={control}
                                onClick={(event) => this.handleClick(event)} href={`gameStart`}>Submit</Button>
                        </AppBar>
                    </MuiThemeProvider>
                </Grid>
            </Grid>
        );
    }

    private handleClick(event: any) {

    }
}

const control = {
    margin: 15,
    backgroundColor: 'white'
};

export default Game;

const appBar = {
    width: 800
};