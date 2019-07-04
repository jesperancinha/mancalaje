import React from "react";
import MancalaBoard from "../../components/MancalaBoard";
import {BoardManager} from "../../types";
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import {theme} from "../../theme";
import {Typography} from "@material-ui/core";
import logo from "../../home/logo.svg";
import {connect} from "react-redux";
import {State} from "../../reducers/reducerIndex";
import MancalaJeHeader from "../../components/MancalaJeHeader";
import {makePutRequest} from "../../actions/OAuthRouting";

interface GameStartProps extends State {
    mancalaReducer: any
    boardManager: BoardManager
    id: number
    match: any
}

class GameStart extends React.Component<GameStartProps, GameStartProps> {

    constructor({props}: { props: GameStartProps }) {
        super(props);
    }

    componentDidMount() {
        makePutRequest('/mancala/boards/' + this.props.match.params.id, this.props,
            (data: any) => this.setState({
                boardManager: data
            }), null);
    }

    render() {
        return (<MancalaJeHeader>
            {
                this.state ? (<MuiThemeProvider theme={theme}>
                        <Typography variant="h2">You are currently playing mancala with</Typography>
                        <span>---</span>
                        <MancalaBoard data={this.state.boardManager}/>
                    </MuiThemeProvider>)
                    : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)
            }
        </MancalaJeHeader>)
    }
}


const mapStateToProps = (state: GameStartProps) => {
    return {
        oauth: state.mancalaReducer.oauth,
        router: state.router
    }
};
// @ts-ignore
export default connect(mapStateToProps)(GameStart);