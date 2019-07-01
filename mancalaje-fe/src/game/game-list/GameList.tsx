import React from 'react';
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {Button, Typography} from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import {Link} from "react-router-dom";
import {control, theme} from "../../theme";
import {RoomComponentIcon} from "../../components/Icons";
import {BoardManager, Game} from "../../types";
import logo from "../../home/logo.svg";

class GameList extends React.Component<Game, Game> {

    componentDidMount() {
        this.loadAllBoards();
    }
    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <Typography variant="h1">Let the games begin!</Typography>
                <Typography component="h2" variant="h2">We're sorry, but MancalaJe isn't ready yet. Please try again
                    later!</Typography>
                {this.state ? (
                    <List component="nav" aria-label="Game room list">
                        {this.state.boardManagers.map(row => (
                            <ListItem key={row.boardManagerId}>
                                <ListItemIcon>
                                    <RoomComponentIcon/>
                                </ListItemIcon>
                                <Link to={`gameStart`}>{row.board.name}</Link>
                            </ListItem>
                        ))}
                    </List>) : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)}
                <Button
                    style={control}
                    onClick={(event) => this.handleClick(event)}
                >Submit</Button>
            </MuiThemeProvider>
        );
    }

    private handleClick(event: any) {
        let messageBody = JSON.stringify({
            boardName: 'This is a good board name'
        });
        console.log(messageBody);
        fetch('mancala/boards', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: messageBody
        })
            .then(res => res.json())
            .then((data: BoardManager) => {
                this.loadAllBoards()
            })
            .catch(console.log);
    }


    private loadAllBoards() {
        fetch('mancala/boards/all')
            .then(res => res.json())
            .then((data: Game) => {
                this.setState(data);
            })
            .catch(console.log)
    }


}

export default GameList