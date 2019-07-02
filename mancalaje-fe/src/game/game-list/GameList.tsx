import React from 'react';
import {Button, Typography} from "@material-ui/core";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import {appBar, control} from "../../theme";
import {RoomComponentIcon} from "../../components/Icons";
import {BoardManager, Game} from "../../types";
import logo from "../../home/logo.svg";
import AppBar from "@material-ui/core/AppBar";
import './../../index.css';
import TextField from "@material-ui/core/TextField";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import MancalaJeHeader from "../../components/MancalaJeHeader";
import {Link} from "react-router-dom";


interface GameListProps extends State {
    game: Game
    mancalaReducer: any
}

class GameList extends React.Component<GameListProps, GameListProps> {

    boardName: any = '';

    constructor({props}: { props: GameListProps }) {
        super(props);
    }

    componentDidMount() {
        this.loadAllBoards();
    }

    componentWillReceiveProps(nextProps: any) {
        console.log(nextProps);
    }

    render() {
        return (
            <MancalaJeHeader>
                <AppBar title="Game room center" position="relative" style={appBar}>
                    <Typography variant="h1">Let the games begin!</Typography>
                    <Typography component="h2" variant="h2">We're sorry, but MancalaJe isn't ready yet. Please try
                        again
                        later!</Typography>
                    {this.state ? (
                        <List component="nav" aria-label="Game room list">
                            {this.state.game.boardManagers.map(row => (
                                <ListItem key={row.boardManagerId}>
                                    <ListItemIcon>
                                        <RoomComponentIcon/>
                                    </ListItemIcon>
                                    <Link to={`gameStart/${row.boardManagerId}`}>{row.board.name}</Link>
                                </ListItem>
                            ))}
                        </List>) : (
                        <h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)}
                    <TextField
                        style={control}
                        label={'Room name'}
                        onChange={(newValue) => {
                            this.boardName = newValue.target.value
                        }}/>
                    <br/>
                    <Button
                        style={control}
                        onClick={(event) => this.handleClick(event)}>Submit</Button>
                </AppBar>
            </MancalaJeHeader>
        );
    }

    private handleClick(event: any) {
        let messageBody = JSON.stringify({
            boardName: this.boardName
        });
        this.props.oauth.fetch('mancala/boards', {
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
        this.props.oauth.fetch('mancala/boards/all')
            .then((res: any) => res.json())
            .then((data: Game) => {
                this.setState({
                    game: data
                });
            })
            .catch(console.log)
    }
}

// function mapDispatchToProps(dispatch: any) {
//     return {actions: bindActionCreators(mancalaReducer, dispatch)}
// }

const mapStateToProps = (state: GameListProps) => {
    return {
        oauth: state.mancalaReducer.oauth,
        router: state.router
    }
};
// @ts-ignore
export default connect(mapStateToProps)(GameList);