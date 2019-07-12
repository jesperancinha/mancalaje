import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import OAuth2 from 'fetch-mw-oauth2';
import {bindActionCreators, Dispatch} from "redux";
import {connect} from "react-redux";
import {createOAuth} from "../../index";
import {State} from "../../reducers/reducerIndex";
import mancalaReducer from "../../reducers/reducer";
import MancalaJeHeader from "../../components/MancalaJeHeader";
import {MySnackbarContentWrapper} from "../../components/SnackbarContent";
import {control} from "../../theme";

interface GameProps extends State {
    username: string;
    password: string;
}

class GameLogin extends Component<GameProps, GameProps> {
    constructor({props}: { props: GameProps }) {
        super(props);
        this.state = {
            username: 'playerOne@mancalaje.com',
            password: 'admin123',
            refreshers: [],
        };
    }

    render() {
        return (
            <MancalaJeHeader>
                <Grid item xs={12}>
                    <AppBar title="Login" position="relative">
                        <Typography variant="h3" align={"center"}>Please login to start playing!</Typography>
                        <TextField
                            style={control}
                            label={'Username'}
                            error={this.state.username.length === 0}
                            helperText={this.getUserHelperText()}
                            onChange={(newValue) => this.setState({username: newValue.target.value, statusError: ''})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={'Password'}
                            type="password"
                            error={this.state.password.length === 0}
                            helperText={this.getPassordHelperText()}
                            onChange={(newValue) => this.setState({password: newValue.target.value, statusError: ''})}/>
                        <br/>
                        <Button
                            style={control}
                            onClick={() => this.props.dispatch(createOAuth(this.handleClick()))}>Submit</Button>

                    </AppBar>
                </Grid>
                <Grid item xs={12}>
                    <AppBar title={"Login cheat"} position={"relative"}>
                        <Typography variant="h4" align={"center"}>Pssst! Don't tell anyone but the username and password
                            is
                            playerOne@mancalaje.com/admin123</Typography>
                    </AppBar>
                </Grid>
                {this.state.statusError ? (
                    <Grid item xs={12}>
                        <MySnackbarContentWrapper
                            variant="error"
                            message={this.state.statusError}
                            onClose={() => this.setState({
                                statusError: ''
                            })}
                        />
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
        }).catch(() => this.setState({
            statusError: 'Login failed!'
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
