import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import {appBar, control, theme} from "../../theme";

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
            <MuiThemeProvider theme={theme}>
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
                        <div style={control}>---</div>
                    </Grid>
                    <Grid item xs={12}>
                        <AppBar title="Login" position="relative" style={appBar}>
                            <Typography variant="h3" align={"center"}>Please login to start playing!</Typography>
                            <TextField
                                style={control}
                                label={'Username'}
                                onChange={(newValue) => this.setState({username: newValue.target.value})}/>
                            <br/>
                            <TextField
                                style={control}
                                label={'Password'}
                                type="password"
                                onChange={(newValue) => this.setState({password: newValue.target.value})}/>
                            <br/>
                            <Button
                                style={control}
                                onClick={(event) => this.handleClick(event)} href={`gameList`}>Submit</Button>
                        </AppBar>
                    </Grid>
                </Grid>
            </MuiThemeProvider>
        );
    }

    private handleClick(event: any) {
        // console.log(this.state);
    }
}

export default Game;
