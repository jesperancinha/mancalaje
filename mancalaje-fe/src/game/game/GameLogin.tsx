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
    dispatch: any;

}

class GameLogin extends Component<any, any> {
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
                                }}
                                // href={`gameList`}
                            >Submit</Button>)}/>

                    </AppBar>
                </Grid>
                <Grid item xs={12}>
                    <Typography variant="h4" align={"center"}>Pssst! Don't tell anyone but the username and password is
                        playerOne@mancalaje.com/admin123</Typography>
                </Grid>
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
        });
        return oAuth2;
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
