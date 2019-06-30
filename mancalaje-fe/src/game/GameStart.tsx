import React from 'react';
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "../theme";
import {Typography} from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import {RoomComponentIcon} from "../components/Icons";


class Board {
    name: string = '';
}

class BoardManager {
    board: Board = new Board;
}

class Game {
    boardManagers: BoardManager[] = [];
}

let response: Game = {
    boardManagers: [
        {
            board: {
                name: "Room 1"
            }
        }
    ]
};

class GameStart extends React.Component {
    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <Typography variant="h1">Let the games begin!</Typography>
                <Typography component="h2" variant="h2">We're sorry, but MancalaJe isn't ready yet. Please try again
                    later!</Typography>
                <List component="nav" aria-label="Main mailbox folders">
                    {response.boardManagers.map(row => (
                        <ListItem>
                            <ListItemIcon>
                                <RoomComponentIcon/>
                            </ListItemIcon>
                            {row.board.name}
                        </ListItem>
                    ))}
                </List>
            </MuiThemeProvider>
        );
    }
}

export default GameStart