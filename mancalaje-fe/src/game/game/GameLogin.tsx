import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import {appBar, control} from "../../theme";

import OAuth2 from 'fetch-mw-oauth2';
import {bindActionCreators, Dispatch} from "redux";
import {connect} from "react-redux";
import {createOAuth} from "../../index";
import {State} from "../../reducers/reducerIndex";
import mancalaReducer from "../../reducers/reducer";
import {Route} from "react-router";
import MancalaJeHeader from "../../components/MancalaJeHeader";

interface GameProps extends State {
    username: string;
    password: string;
    dispatch?: any;
    loginError?: string;

}

class GameLogin extends Component<GameProps, GameProps> {
    constructor({props}: { props: GameProps }) {
        super(props);
        this.state = {
            username: '',
            password: ''
        };
    }

    render() {
        return (
            <MancalaJeHeader>
                <Grid item xs={12}>
                    <div style={control}>---</div>
                </Grid>
                <Grid item xs={12}>
                    <AppBar title="Login" position="relative" style={appBar}>
                        <Typography variant="h3" align={"center"}>Please login to start playing!</Typography>
                        <TextField
                            style={control}
                            label={'Username'}
                            error={this.state.username.length === 0}
                            helperText={this.getUserHelperText()}
                            onChange={(newValue) => this.setState({username: newValue.target.value, loginError: ''})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={'Password'}
                            type="password"
                            error={this.state.password.length === 0}
                            helperText={this.getPassordHelperText()}
                            onChange={(newValue) => this.setState({password: newValue.target.value, loginError: ''})}/>
                        <br/>
                        <Route render={({history}) => (
                            <Button
                                style={control}
                                onClick={(event) => {
                                    this.props.dispatch(createOAuth(this.handleClick()));
                                }}
                                // href={`gameList`}
                            >Submit</Button>)}/>

                    </AppBar>
                </Grid>
                <Grid item xs={12}>
                    <Typography variant="h4" align={"center"}>Pssst! Don't tell anyone but the username and password is
                        playerOne@mancalaje.com/admin123</Typography>
                </Grid>
                {this.state.loginError ? (
                    <Grid item xs={12}>
                        <AppBar title="Login status" position="relative">
                            <Typography align="center" component="h1" variant="h1">{this.state.loginError}</Typography>
                        </AppBar>
                    </Grid>) : <div/>}
            </MancalaJeHeader>
        );
    }

    private handleClick() {
        let oAuth2 = new OAuth2({
            grantType: 'password',
            clientId: 'mancala-client',
            clientSecret: 'mancala',
            userName: this.state.username,
            password: this.state.password,
            tokenEndpoint: 'http://localhost:3000/oauth/token',
        });
        oAuth2.getAccessToken().then(() => {
            this.props.history.push('gameList');
        }).catch(error => this.setState({
            loginError: 'Login failed!'
        }));
        return oAuth2;
    }

    private getUserHelperText() {
        if (this.state.username.length === 0) {
            return "Please enter a username"
        }
    }

    private getPassordHelperText() {
        if (this.state.password.length === 0) {
            return "Please enter a password"
        }
    }
}

function mapDispatchToProps(dispatch: Dispatch) {
    return {actions: bindActionCreators(mancalaReducer, dispatch)}
}


// const mapStateToProps = (state: GameProps) => ({
//     username: state.username,
//     password: state.password,
//     dispatch: state.dispatch,
//     pathname: state.router.location.pathname,
//     search: state.router.location.search,
//     hash: state.router.location.hash,
//     oauth: state.oauth,
//     router: state.router
// });

// @ts-ignore
export default connect(mapDispatchToProps)(GameLogin)
