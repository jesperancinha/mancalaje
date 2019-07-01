import React from "react";
import MancalaBoard from "../../components/MancalaBoard";
import {BoardManager} from "../../types";

class GameStart extends React.Component<BoardManager, BoardManager> {

    componentDidMount() {
        fetch('http://localhost:3000/mancala/boards/list')
            .then(res => res.json())
            .then((data: BoardManager) => {
                console.log(data);
                this.setState(data);
            })
            .catch(console.log)
    }

    render() {
        return (
            <MancalaBoard data={this.state}/>
        )
    }
}

export default GameStart;