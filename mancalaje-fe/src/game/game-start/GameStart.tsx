import React from "react";
import MancalaBoard from "../../components/MancalaBoard";
import {BoardManager} from "../../types";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "../../theme";
import {Typography} from "@material-ui/core";
import logo from "../../home/logo.svg";

class GameStart extends React.Component<BoardManager, BoardManager> {

    componentDidMount() {
        fetch('mancala/boards')
            .then(res => res.json())
            .then((data: BoardManager) => {
                this.setState(data);
            })
            .catch(console.log)
    }

    render() {
        return (<div>
            {
                this.state ? (<MuiThemeProvider theme={theme}>
                        <Typography variant="h1">MancalaJe</Typography>
                        <Typography variant="h2">You are currently playing mancala with</Typography>
                        <span>---</span>
                        <MancalaBoard data={this.state}/>
                    </MuiThemeProvider>)
                    : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)
            }
        </div>)
    }
}

export default GameStart;