import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import {appBar, control, theme} from "../../theme";

import OAuth2 from 'fetch-mw-oauth2';
import {bindActionCreators, Dispatch} from "redux";
import {connect} from "react-redux";
import {createOAuth, store} from "../../index";
import {State} from "../../reducers/reducerIndex";
import mancalaReducer from "../../reducers/reducer";
import {Route} from "react-router";

interface GameProps extends State {
    username: string;
    password: string;
    dispatch: any;

}

class Game extends Component<any, any> {
    constructor({props}: { props: GameProps }) {
        super(props);
        this.state = {
            username: 'playerOne@mancalaje.com',
            password: 'admin123'
        };
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
                            <Route render={({history}) => (
                                <Button
                                    style={control}
                                    onClick={(event) => {
                                        this.props.dispatch(createOAuth(this.handleClick()));
                                        history.push('gameList');
                                    }}
                                    // href={`gameList`}
                                >Submit</Button>)}/>

                        </AppBar>
                    </Grid>
                </Grid>
            </MuiThemeProvider>
        );
    }

    private handleClick() {
        return new OAuth2({
            grantType: 'password',
            clientId: 'mancala-client',
            clientSecret: 'mancala',
            userName: this.state.username,
            password: this.state.password,
            tokenEndpoint: 'http://localhost:3000/oauth/token',
        });
    }

}

function mapDispatchToProps(dispatch: Dispatch) {
    return {actions: bindActionCreators(mancalaReducer, dispatch)}
}


const mapStateToProps = (state: GameProps) => ({
    username: state.username,
    password: state.password,
    dispatch: state.dispatch,
    pathname: state.router.location.pathname,
    search: state.router.location.search,
    hash: state.router.location.hash,
    oauth: state.oauth,
    router: state.router
});

// @ts-ignore
export default connect(mapDispatchToProps)(Game)
