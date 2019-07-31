import AppBar from '@material-ui/core/AppBar';
import TextField from '@material-ui/core/TextField';
import React, {Component} from 'react';
import {Button, Grid} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import {bindActionCreators, Dispatch} from "redux";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import {mancalaReducer} from "../../reducers/reducer";
import {MySnackbarContentWrapper} from "../../components/SnackbarContent";
import {control, XS_COL_SPAN} from "../../theme";
import {MancalaJeHeader} from "../../components/MancalaJeHeader";
import {removeAllRefreshers} from "../../actions/Refresher";
import {invalidateEmail, invalidateText} from "../../actions/Validators";
import {home, makePostRequest} from "../../actions/OAuthRouting";
import {NewUser} from "../../entities/new-user";

interface GameRegisterProps extends State {
    name: string;
    email: string;
    password: string;
    confirmPassword: string;
}

class GameRegister extends Component<GameRegisterProps, GameRegisterProps> {
    constructor({props}: { props: GameRegisterProps }) {
        super(props);
        this.state = {
            confirmPassword: "",
            email: "",
            name: "",
            password: "",
            refreshers: [],
        };
    }

    public componentDidMount(): void {
        removeAllRefreshers();
    }

    public render(): {} {
        return (
            <MancalaJeHeader>
                <Grid item xs={XS_COL_SPAN}>
                    <AppBar title="Login" position="relative">
                        <Typography variant="h3" align={"center"}>Please register to start playing!</Typography>
                        <TextField
                            style={control}
                            label={"Name"}
                            error={invalidateText(this.state.name)}
                            helperText={this.getUserHelperText()}
                            onChange={(newValue) => this.setState({name: newValue.target.value, statusError: ""})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={"Email"}
                            error={invalidateText(this.state.email) || invalidateEmail(this.state.email)}
                            helperText={this.getEmailHelperText()}
                            onChange={(newValue) => this.setState({email: newValue.target.value, statusError: ""})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={"Password"}
                            type="password"
                            error={invalidateText(this.state.password)}
                            helperText={this.getPassordHelperText()}
                            onChange={(newValue) => this.setState({password: newValue.target.value, statusError: ""})}/>
                        <br/>
                        <TextField
                            style={control}
                            label={"Confirm password"}
                            type="password"
                            error={invalidateText(this.state.confirmPassword)
                            || this.state.confirmPassword !== this.state.password}
                            helperText={this.getConfirmPasswordHelperText()}
                            onChange={(newValue) => this.setState({
                                confirmPassword: newValue.target.value,
                                statusError: "",
                            })}/>
                        <br/>
                        <Button
                            disabled={this.canRegister()}
                            style={control}
                            onClick={() => this.handleClick()}>Submit</Button>
                        <Button
                            style={control}
                            onClick={() => home(this.props)}>Back to home</Button>
                    </AppBar>
                </Grid>
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
                <Grid item xs={XS_COL_SPAN}>
                    <AppBar title={"Login cheat"} position={"relative"}>
                        <Typography variant="h6" align={"center"}>
                            Remember that your user will be available for 5 hours.
                            Everytime you play, your user registration will be prolonged for an extra 5 hours. While you
                            are playing there is no expiry date and it will be reset to 5 hour later after you played.
                            Should you want more time in registration, remember that this is just a demo
                            version.
                        </Typography>
                    </AppBar>
                </Grid>
            </MancalaJeHeader>
        );
    }

    private handleClick(): void {
        const user = new NewUser();
        user.name = this.state.name;
        user.email = this.state.email;
        user.password = this.state.password;
        console.log(user);
        makePostRequest('/mancala/users', this.state, this.props,
            () => this.props.history && this.props.history.push(`login`),
            JSON.stringify(user), (errorMessage: string) => this.setState({
                statusError: errorMessage,
            }));
    }

    private getUserHelperText(): string {
        if (invalidateText(this.state.name)) {
            return "Please enter a username";
        }
        return '';
    }

    private getEmailHelperText(): string {
        const email = this.state.email;
        if (invalidateText(email)) {
            return "Please enter an email";
        }
        if(invalidateEmail(email)) {
            return "Please enter a valid email"
        }
        return '';
    }

    private getPassordHelperText(): string {
        if (invalidateText(this.state.password)) {
            return "Please enter a password";
        }
        return '';
    }

    private getConfirmPasswordHelperText(): string {
        if (invalidateText(this.state.confirmPassword)) {
            return "Please confirm your password";
        }
        if (this.state.confirmPassword !== this.state.password) {
            return "Your password must be the same as your password confirmation!";
        }
        return '';
    }

    private canRegister(): boolean {
        const mail = this.state.email;
        return invalidateText(this.state.name)
            || invalidateText(mail)
            || invalidateEmail(mail)
            || invalidateText(this.state.password)
            || invalidateText(this.state.confirmPassword)
            || this.state.password !== this.state.confirmPassword;
    }

}

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {actions: bindActionCreators(mancalaReducer, dispatch)}
};


// const mapStateToProps = (state: GameRegisterProps) => ({
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
const GameRegisterConnected = connect(mapDispatchToProps)(GameRegister);
export {GameRegisterConnected}



