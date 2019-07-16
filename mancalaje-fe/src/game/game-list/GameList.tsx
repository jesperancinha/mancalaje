import React from 'react';
import {Button, Grid, Typography} from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import {control, XS_COL_SPAN} from "../../theme";
import {ListItemLink, RemoveComponentIcon, RoomComponentIcon} from "../../components/Icons";
import logo from "../../home/logo.svg";
import AppBar from "@material-ui/core/AppBar";
import TextField from "@material-ui/core/TextField";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import {logOut, makeDeleteRequest, makeGetRequest, makePostRequest, makePutRequest} from "../../actions/OAuthRouting";
import {createOAuth} from "../../index";
import {MySnackbarContentWrapper} from "../../components/SnackbarContent";
import Box from "@material-ui/core/Box";
import {Game} from "../../entities/game";
import {PlayerState} from "../../entities/player-state";
import {BoardManager} from "../../entities/board-manager";
import {MancalaJeHeader} from '../../components/MancalaJeHeader';
import {MancalaReducer} from "../../entities/mancala-reducer";


interface GameListProps extends State {
    game?: Game;
    mancalaReducer?: MancalaReducer;
    boardName: string;
    playerState?: PlayerState
}

class GameList extends React.Component<GameListProps, GameListProps> {


    constructor({props}: { props: GameListProps }) {
        super(props);
        this.state = {boardName: "", refreshers: []};
    }

    componentDidMount() {
        this.loadAllBoards();
        makeDeleteRequest("mancala/rooms", this.state, this.props);
        const refresher = setInterval(() => {
            this.loadAllBoards();
        }, 1000);
        this.state.refreshers.push(refresher);
        this.setState(this.state);
    }

    render() {
        return (
            <MancalaJeHeader>
                <AppBar title="Game room center title" position="relative">
                    <Typography variant="h3">Select a room or create one</Typography>
                </AppBar>
                <AppBar title="Game room center warning" position="relative">

                    <Typography component="h4" variant="h4">We're sorry, but MancalaJe isn't ready yet. Please try
                        again
                        later!</Typography>
                </AppBar>
                <AppBar title={"Game list controls"} position={"relative"}>
                    <TextField
                        style={control}
                        label={"Room name"}
                        error={this.state.boardName.length === 0}
                        helperText={this.getRoomNameHelperText()}
                        onChange={(newValue) => this.changeState(newValue.target.value)}/>
                    <br/>
                    <Button
                        style={control}
                        onClick={() => this.handleClick()}>Submit</Button>
                </AppBar>
                {this.state.statusError ? (
                    <Grid item xs={XS_COL_SPAN}>
                        <MySnackbarContentWrapper
                            variant="error"
                            message={this.state.statusError}
                            onClose={() => this.setState({
                                statusError: ""
                            })}
                        />
                    </Grid>) : <div/>}
                <AppBar title="Game room center options" position="relative">
                    {this.state && this.state.game && this.state.game.boardManagers ? (
                        <Box>
                            <Typography variant="h3"
                                        component="h3"
                                        align={"center"}>Listing {this.state.game.boardManagers.length} rooms</Typography>
                            <List component="nav" aria-label="Game room list">
                                {this.state.game.boardManagers.map(row => (
                                    <ListItem key={row.boardManagerId} component={"div"}>
                                        <ListItem component="span" button onClick={() => this.redirectToGamePage(row)}>
                                            <ListItemIcon>
                                                <RoomComponentIcon/>
                                            </ListItemIcon>
                                            {row.board ? row.board.name : ""}
                                        </ListItem>
                                        <ListItemLink onClick={() => this.handleRemoveRoom(row.boardManagerId)}>
                                            <RemoveComponentIcon/>
                                        </ListItemLink>
                                        <ListItem>
                                            {GameList.getCurrentPlayersText(row)}
                                        </ListItem>
                                        <ListItem>
                                            {row.owner ? "Owner:" + row.owner.name : ""}
                                        </ListItem>
                                    </ListItem>
                                ))}
                            </List>
                        </Box>) : (
                        <h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)}
                    <Button
                        style={control}
                        onClick={() => this.logOut()}>Logout</Button>
                </AppBar>
            </MancalaJeHeader>
        );
    }

    private changeState(boardName: string): void {
        this.setState({
            boardName: boardName,
            statusError: "",
        });
    }

    private handleClick() {
        const messageBody = JSON.stringify({
            boardName: this.state.boardName
        });
        makePostRequest('mancala/boards', this.state, this.props, () => this.loadAllBoards(), messageBody);
    }


    private loadAllBoards() {
        makeGetRequest('mancala/boards/all', this.state, this.props, (data: any) => {
            this.setState({
                game: data
            });
        });
    }

    private handleRemoveRoom(roomId: number) {
        makeDeleteRequest("mancala/boards/" + roomId, this.state, this.props, () => this.loadAllBoards());
    }

    private logOut(): void {
        this.props.dispatch(createOAuth());
        logOut(this.props, this.state);
    }

    private getRoomNameHelperText(): string {
        if (!this.state.boardName) {
            return "Room must have a name!";
        }
        return "";
    }

    private redirectToGamePage(row: BoardManager) {
        this.state.refreshers.map(clearInterval);
        makePutRequest("/mancala/rooms/" + row.boardManagerId, this.state, this.props,
            () =>
                this.props.history.push(`gameStart/${row.boardManagerId}`),
            "{}", (errorMessage: string) => this.setState({
                statusError: errorMessage
            }));
    }

    private static getCurrentPlayersText(row: BoardManager): string {
        if (row.board) {
            const player1 = row.board.player1;
            const player2 = row.board.player2;
            if (player1 && player2) {
                return "Current players: " + player1.name + " and " + player2.name;
            }
            if (player1) {
                return "Current player: " + player1.name;
            }
            if (player2) {
                return "Current player: " + player2.name;
            }
        }
        return "No players in the game. Room is free";

    }
}

// function mapDispatchToProps(dispatch: any) {
//     return {actions: bindActionCreators(mancalaReducer, dispatch)}
// }

const mapStateToProps = (state: GameListProps) => {
    return {
        oauth: state.mancalaReducer ? state.mancalaReducer.oauth : null,
        router: state.router,
        refreshers: state.refreshers
    }
};
// @ts-ignore
const GameListConnected = connect(mapStateToProps)(GameList);
export {GameListConnected};