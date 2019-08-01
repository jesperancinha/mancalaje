import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import OAuth2 from 'fetch-mw-oauth2';
import {bindActionCreators, Dispatch} from "redux";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import {mancalaReducer} from "../../reducers/reducer";
import {MySnackbarContentWrapper} from "../../components/SnackbarContent";
import {control, XS_COL_SPAN} from "../../theme";
import {MancalaJeHeader} from "../../components/MancalaJeHeader";
import {invalidateText} from "../../actions/Validators";
import {createOAuth, createPrevMessage} from "../../store";
import {MancalaReducer} from "../../entities/mancala-reducer";

interface GameProps extends State {
    username: string;
    password: string;
    mancalaReducer?: MancalaReducer;
}

class GameLogin extends Component<GameProps, GameProps> {
    constructor({props}: { props: GameProps }) {
        super(props);
        this.state = {
            password: "admin123",
            refreshers: [],
            username: "playerOne@mancalaje.com",
        };
    }

    public componentDidMount(): void {
        const intervalId = setInterval(() => {
        }, 1);
        for (let i = 1; i < intervalId; i++) {
            clearInterval(i);
        }
    }

    public render(): {} {
        return (
            <MancalaJeHeader>
                <Grid item xs={XS_COL_SPAN}>
                    <AppBar title="Login" position="relative">
                        <Typography variant="h3" align={"center"}>Please login to start playing!</Typography>
                        <TextField
                            style={control}
                            label={"Username"}
                            error={invalidateText(this.state.username)}
                            helperText={this.getUserHelperText()}
                            onChange={(newValue) => this.setState({username: newValue.target.value, statusError: ""})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={"Password"}
                            type="password"
                            error={invalidateText(this.state.password)}
                            helperText={this.getPassordHelperText()}
                            onChange={(newValue) => this.setState({password: newValue.target.value, statusError: ""})}/>
                        <br/>
                        <Button
                            style={control}
                            onClick={() =>{
                                debugger;
                                this.props.dispatch ?
                                    this.props.dispatch(createOAuth(this.handleClick())) :
                                    {}}}>
                            Submit
                        </Button>
                        <Button
                            style={control}
                            onClick={() =>
                                this.props.history ?
                                    this.props.history.push("gameRegister") : {}}>
                            Register
                        </Button>
                    </AppBar>
                </Grid>
                {this.props.prevAction ? (
                    <Grid item xs={XS_COL_SPAN}>
                        <MySnackbarContentWrapper
                            variant="info"
                            message={this.props.prevAction}
                            onClose={() => {
                                this.props.dispatch && this.props.dispatch(createPrevMessage());
                                this.setState({
                                    prevAction: "",
                                });
                            }}
                        />
                    </Grid>) : <div/>}
                {this.state.statusError ? (
                    <Grid item xs={XS_COL_SPAN}>
                        <MySnackbarContentWrapper
                            variant="error"
                            message={this.state.statusError}
                            onClose={() => this.setState({
                                statusError: "",
                            })}
                        />
                    </Grid>) : <div/>}
            </MancalaJeHeader>
        );
    }

    private handleClick(): OAuth2 {
        debugger;
        const oAuth2 = new OAuth2({
            clientId: "mancala-client",
            clientSecret: "mancala",
            grantType: "password",
            password: this.state.password,
            tokenEndpoint: "/oauth/token",
            userName: this.state.username,
        });
        oAuth2.getAccessToken().then(
            () =>
                this.props.history ?
                    this.props.history.push("gameList") :
                    {},
        ).catch(() => this.setState({
            statusError: 'Login failed!',
        }));
        return oAuth2;
    }

    private getUserHelperText(): string {
        if (invalidateText(this.state.username)) {
            return "Please enter a username"
        }
        return '';
    }

    private getPassordHelperText(): string {
        if (invalidateText(this.state.password)) {
            return "Please enter a password"
        }
        return '';
    }

}

const mapDispatchToProps = (dispatch: Dispatch) => {
    debugger;
    return {actions: bindActionCreators(mancalaReducer, dispatch)}
};

const mapStateToProps = (state: GameProps) => {
    debugger;
    return {
        prevAction: state.mancalaReducer && state.mancalaReducer.prevAction,
    }
};
// @ts-ignore
const GameLoginConnected = connect(mapDispatchToProps)(connect(mapStateToProps)(GameLogin));
export {GameLoginConnected}
