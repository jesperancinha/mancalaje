import {holeDisabled, holeEnabled, useStyles} from "../theme";
import {Paper} from "@material-ui/core";
import * as React from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import logo from "../home/logo.svg";
import "./MancalaBoard.css"
import {makePutRequest} from "../actions/OAuthRouting";
import {State} from "../reducers/reducerIndex";
import Button from "@material-ui/core/Button";
import {Hole} from "../entities/hole";
import {BoardManager} from "../entities/board-manager";

let swayStones = function (id: any, state: State, props: any) {
    state.statusError = '';
    makePutRequest('/mancala/actions/nextMove/' + id, state, props, () => {
    }, null);
};

let dataBoardManager: any;
const MancalaBoard = ({data, state, props}: { data?: BoardManager, state?: any, props?: any }) => {

    dataBoardManager = data;
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Paper>
                {data && data.board && data.board.allHoles ? (
                    <Table>
                        <TableBody>
                            <TableRow>
                                <TableCell
                                    style={dataBoardManager.board.allHoles[13].enabled ? holeEnabled : holeDisabled}
                                    rowSpan={2} colSpan={1}>
                                    <Button
                                        disabled={!dataBoardManager.board.allHoles[13].enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                        onClick={() => swayStones(dataBoardManager.board.allHoles[13].id, state, props)}
                                        href={"#"}>{data.board.allHoles[13].stones}</Button></TableCell>
                                {dataBoardManager.board.allHoles.slice(7, 13).reverse().map((hole: Hole) => (
                                    <TableCell
                                        style={hole.enabled ? holeEnabled : holeDisabled}
                                        rowSpan={1} colSpan={1}>
                                        <Button
                                            disabled={!hole.enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                            onClick={() => swayStones(hole.id, state, props)}
                                            href={"#"}>{hole.stones}</Button></TableCell>
                                ))}
                                <TableCell
                                    style={dataBoardManager.board.allHoles[6].enabled ? holeEnabled : holeDisabled}
                                    rowSpan={2} colSpan={1}>
                                    <Button
                                        disabled={!dataBoardManager.board.allHoles[6].enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                        onClick={() => swayStones(dataBoardManager.board.allHoles[6].id, state, props)}
                                        href={"#"}>{data.board.allHoles[6].stones}</Button></TableCell>
                            </TableRow>
                            <TableRow>
                                {dataBoardManager.board.allHoles.slice(0, 6).map((hole: Hole) => (
                                    <TableCell
                                        style={hole.enabled ? holeEnabled : holeDisabled}
                                        rowSpan={1} colSpan={1}>
                                        <Button
                                            disabled={!hole.enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                            onClick={() => swayStones(hole.id, state, props)}
                                            href={"#"}>{hole.stones}</Button></TableCell>
                                ))}
                            </TableRow>
                        </TableBody>
                    </Table>
                ) : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)}
            </Paper>
        </div>
    );
};

export {MancalaBoard};
