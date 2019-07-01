import React from 'react';
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {Typography} from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import {Link} from "react-router-dom";
import {theme} from "../../theme";
import {RoomComponentIcon} from "../../components/Icons";
import {Game} from "../../types";

let response: Game = {
    boardManagers: [
        {
            board: {
                name: "Room 1",
                allHoles: []
            }
        }
    ]
};

class GameList extends React.Component {
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
                            <Link to={`gameStart`}>{row.board.name}</Link>
                        </ListItem>
                    ))}
                </List>
            </MuiThemeProvider>
        );
    }
}

export default GameList