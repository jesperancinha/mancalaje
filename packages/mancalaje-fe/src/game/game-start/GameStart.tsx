import React from "react";
import {control, theme, XS_COL_SPAN} from "../../theme";
import {Button, Grid, MuiThemeProvider, Typography} from "@material-ui/core";
import logo from "../../home/logo.svg";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import {makeDeleteRequest, makeGetRequest} from "../../actions/OAuthRouting";
import AppBar from "@material-ui/core/AppBar";
import {MySnackbarContentWrapper} from "../../components/SnackbarContent";
import {PlayerState} from "../../entities/player-state";
import {MancalaJeHeader} from "../../components/MancalaJeHeader";
import {MancalaBoard} from "../../components/MancalaBoard";
import {MancalaReducer} from "../../entities/mancala-reducer";
import {REFRESH_RATE} from "../../actions/Refresher";
import {Match} from "../../entities/match";

export interface GameStartProps extends State {
    mancalaReducer?: MancalaReducer
    playerState?: PlayerState
    id?: number
    match?: Match
}

class GameStart extends React.Component<GameStartProps, GameStartProps> {

    constructor({props}: { props: GameStartProps }) {
        super(props);
        this.state = {
            refreshers: [],
        }
    }

    public componentDidMount(): void {
        this.loadGameData();
        const refresher = setInterval(() => {
            this.loadGameData();
        }, REFRESH_RATE);
        // this.state.refreshers.push(refresher);

    }

    public render(): {} {
        return (<MancalaJeHeader>
            {
                this.state && this.state.playerState ? (<MuiThemeProvider theme={theme}>
                        {this.state.playerState.loggedPlayer
                        && this.state.playerState.boardManager
                        && this.state.playerState.boardManager.board ?
                            (<div>
                                <AppBar title="Game Start Title" position="relative">
                                    <Typography variant="h2">
                                        Hello {this.state.playerState.loggedPlayer.name}
                                    </Typography>
                                </AppBar>
                                <AppBar position={"relative"}>
                                    <Typography variant="h2">
                                        You are in room {this.state.playerState.boardManager.board.name}
                                    </Typography>
                                </AppBar>
                                {!this.state.playerState.boardManager.gameover ?
                                    (this.state.playerState.loggedPlayer.opponentName ?
                                            (<AppBar position={"relative"}>
                                                <Typography variant="h3">
                                                    You are currently playing mancalaje
                                                    with {this.state.playerState.loggedPlayer.opponentName}
                                                </Typography>
                                            </AppBar>) : (
                                                <AppBar position={"relative"}>
                                                    <Typography variant="h3">
                                                        Waiting for player to join room...
                                                    </Typography>
                                                </AppBar>)

                                    ) : (<div/>)}
                            </div>) : (<AppBar title="Game Start Loading Title" position="relative">
                                <Typography variant="h2">Loading...</Typography></AppBar>)}
                        {this.state.playerState.boardManager
                        && !this.state.playerState.boardManager.gameover
                        && this.state.playerState.boardManager.currentPlayer ? (
                            <div>
                                <AppBar title="Game Start Current Player" position="relative">
                                    <Typography variant="h3">Current
                                        player {this.state.playerState.boardManager.currentPlayer.name}
                                    </Typography>
                                </AppBar>
                                <AppBar title="Game Start Board" position="relative">
                                    <MancalaBoard data={this.state.playerState.boardManager}
                                                  state={this.state}
                                                  props={this.props}/>
                                </AppBar>
                            </div>) : (<div/>)}
                        {this.state.playerState.boardManager &&
                        this.state.playerState.boardManager.winner && this.state.playerState.boardManager.gameover ? (
                                <AppBar title={'Game Over!'} position={"relative"}>
                                    <Typography variant="h3">Game Over! The winner
                                        is {this.state.playerState.boardManager.winner.name}</Typography>
                                </AppBar>)
                            : (<div/>)}


                        <AppBar title={"Game Start Controls"} position={"relative"}>
                            <Button
                                style={control}
                                onClick={() => this.leaveRoom()}>Leave room</Button>
                        </AppBar>
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
                    </MuiThemeProvider>)
                    : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)
            }
        </MancalaJeHeader>)
    }


    private loadGameData(): void {
        if (this.props.match) {
            makeGetRequest("/boards/" + this.props.match.params.id, this.state, this.props,
                (data: PlayerState) => this.setState({
                    playerState: data,
                }));
        }
    }

    private leaveRoom(): void {
        this.state.refreshers.forEach(clearInterval);
        if (this.props.match) {
            makeDeleteRequest("/rooms/" + this.props.match.params.id, this.state, this.props,
                () => this.props.history ? this.props.history.push(`/gameList`) : {});
        }
    }
}

const mapStateToProps = (state: GameStartProps) => {
    return {
        oauth: state.mancalaReducer ? state.mancalaReducer.oauth : '',
        refreshers: state.refreshers,
        router: state.router,
    }
};
// @ts-ignore
const GameStartConnected = connect(mapStateToProps)(GameStart);

export {GameStart};
