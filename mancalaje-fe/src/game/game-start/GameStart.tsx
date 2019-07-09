import React from "react";
import MancalaBoard from "../../components/MancalaBoard";
import {PlayerState} from "../../types";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {control, theme} from "../../theme";
import {Button, Typography} from "@material-ui/core";
import logo from "../../home/logo.svg";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import MancalaJeHeader from "../../components/MancalaJeHeader";
import {makeDeleteRequest, makeGetRequest} from "../../actions/OAuthRouting";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";

interface GameStartProps extends State {
    mancalaReducer?: any
    playerState?: PlayerState
    id?: number
    match?: any
}

class GameStart extends React.Component<GameStartProps, GameStartProps> {

    constructor({props}: { props: GameStartProps }) {
        super(props);
        this.state = {
            refreshers: []
        }
    }

    componentDidMount() {
        this.loadGameData();
        let refresher: number = setInterval(() => {
            this.loadGameData();
        }, 1000);
        this.state.refreshers.push(refresher);

    }

    private loadGameData() {
        makeGetRequest('/mancala/boards/' + this.props.match.params.id, this.state, this.props,
            (data: any) => this.setState({
                playerState: data
            }));
    }

    render() {
        return (<MancalaJeHeader>
            {
                this.state && this.state.playerState ? (<MuiThemeProvider theme={theme}>
                        <AppBar title="Game Start Title" position="relative">
                            {this.state.playerState.loggedPlayer ?
                                (<Box>
                                    <Typography variant="h2">Hello {this.state.playerState.loggedPlayer.name}</Typography>
                                    {this.state.playerState.loggedPlayer.opponentName ?
                                        (<Typography variant="h2">You are currently playing mancalaje
                                            with {this.state.playerState.loggedPlayer.opponentName}</Typography>) : (
                                            <Typography variant="h2">Waiting for player to join room...</Typography>)

                                    }
                                </Box>) : (<Typography variant="h2">Loading...</Typography>)}

                        </AppBar>
                        <AppBar title="Game Start Board" position="relative">
                            <MancalaBoard data={this.state.playerState.boardManager} state={this.state} props={this.props}/>
                        </AppBar>
                        <AppBar title={'Game Start Controls'} position={"relative"}>
                            <Button
                                style={control}
                                onClick={() => this.leaveRoom()}>Leave room</Button>
                        </AppBar>
                    </MuiThemeProvider>)
                    : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)
            }
        </MancalaJeHeader>)
    }

    private leaveRoom() {
        this.state.refreshers.forEach(clearInterval);
        makeDeleteRequest('/mancala/rooms/' + this.props.match.params.id, this.state, this.props, () => {
            this.props.history.push(`/gameList`)
        });
    }
}


const mapStateToProps = (state: GameStartProps) => {
    return {
        oauth: state.mancalaReducer.oauth,
        router: state.router,
        refreshers: state.refreshers
    }
};
// @ts-ignore
export default connect(mapStateToProps)(GameStart);