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
import {PlayerState} from "../../entities/player-state";
import {BoardManager} from "../../model/board-manager";
import {MancalaJeHeader} from '../../components/MancalaJeHeader';
import {MancalaReducer} from "../../entities/mancala-reducer";
import {REFRESH_RATE} from "../../actions/Refresher";
import {invalidateText} from "../../actions/Validators";


interface GameListProps extends State {
    games?: PlayerState[];
    mancalaReducer?: MancalaReducer;
    boardName: string;
    playerState?: PlayerState
}

class GameList extends React.Component<GameListProps, GameListProps> {


    constructor({props}: { props: GameListProps }) {
        super(props);
        this.state = {boardName: "", refreshers: []};
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

    public componentDidMount(): void {
        this.loadAllBoards();
        makeDeleteRequest("mancala/rooms", this.state, this.props);
        const refresher = setInterval(() => {
            this.loadAllBoards();
        }, REFRESH_RATE);
        this.state.refreshers.push(refresher);
        this.setState(this.state);
    }

    public render(): {} {
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
                        error={invalidateText(this.state.boardName)}
                        helperText={this.getRoomNameHelperText()}
                        onChange={(newValue) => this.changeState(newValue.target.value)}/>
                    <br/>
                    <Button
                        disabled={invalidateText(this.state.boardName)}
                        style={control}
                        onClick={() => this.handleClick()}>Submit</Button>
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
                <AppBar title="Game room center options" position="relative">
                    {this.state && this.state.games ? (
                        <Box>
                            <Typography variant="h3"
                                        component="h3"
                                        align={"center"}>
                                Listing {this.state.games.length} rooms
                            </Typography>
                            <List component="nav" aria-label="Game room list">
                                {this.state.games.map(row => (
                                    <ListItem key={row.boardManager.boardManagerId.valueOf()} component={"div"}>
                                        <ListItem component="span"
                                                  button
                                                  onClick={() =>
                                                      this.redirectToGamePage(row.boardManager)}>
                                            <ListItemIcon>
                                                <RoomComponentIcon/>
                                            </ListItemIcon>
                                            {row.boardManager.board ? row.boardManager.board.name : ""}
                                        </ListItem>
                                        <ListItemLink
                                            onClick={() =>
                                                this.handleRemoveRoom(row.boardManager.boardManagerId.valueOf())}>
                                            <RemoveComponentIcon/>
                                        </ListItemLink>
                                        <ListItem>
                                            {GameList.getCurrentPlayersText(row.boardManager)}
                                        </ListItem>
                                        <ListItem>
                                            {row.boardManager.owner ? "Owner:" + row.boardManager.owner.name : ""}
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


    private handleClick(): void {
        const messageBody = JSON.stringify({
            boardName: this.state.boardName,
        });
        makePostRequest('mancala/boards', this.state, this.props, () => this.loadAllBoards(), messageBody);
    }

    private loadAllBoards(): void {
        makeGetRequest('mancala/boards/all', this.state, this.props, (data: PlayerState[]) => {
            this.setState({
                games: data,
            });
        });
    }

    private handleRemoveRoom(roomId: number): void {
        makeDeleteRequest("mancala/boards/" + roomId, this.state, this.props, () => this.loadAllBoards());
    }

    private logOut(): void {
        if (this.props.dispatch) {
            this.props.dispatch(createOAuth());
        }
        logOut(this.props, this.state);
    }

    private getRoomNameHelperText(): string {
        if (!this.state.boardName) {
            return "Room must have a name!";
        }
        return "";
    }

    private redirectToGamePage(row: BoardManager): void {
        this.state.refreshers.map(clearInterval);
        makePutRequest("/mancala/rooms/" + row.boardManagerId, this.state, this.props,
            () => this.props.history ? this.props.history.push(`gameStart/${row.boardManagerId}`) : {},
            "{}", (errorMessage: string) => this.setState({
                statusError: errorMessage,
            }));
    }
}

// function mapDispatchToProps(dispatch: any) {
//     return {actions: bindActionCreators(mancalaReducer, dispatch)}
// }

const mapStateToProps = (state: GameListProps) => {
    return {
        oauth: state.mancalaReducer ? state.mancalaReducer.oauth : null,
        refreshers: state.refreshers,
        router: state.router,
    }
};
// @ts-ignore
const GameListConnected = connect(mapStateToProps)(GameList);

export {GameListConnected};
