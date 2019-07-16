import {holeDisabled, holeEnabled, useStyles} from "../theme";
import {Paper} from "@material-ui/core";
import * as React from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import logo from "../home/logo.svg";
import "./MancalaBoard.sass"
import {makePutRequest} from "../actions/OAuthRouting";
import {State} from "../reducers/reducerIndex";
import Button from "@material-ui/core/Button";
import {Hole} from "../entities/hole";
import {BoardManager} from "../entities/board-manager";
import {GameStartProps} from "../game/game-start/GameStart";

const swayStones = (id: number, state: State, props: State) => {
    state.statusError = "";
    makePutRequest("/mancala/actions/nextMove/" + id, state, props);
};
const MAX_STONE_INDEX = 13;
const STORE_ROW_SPAN = 2;
const SINGLE_SPAN = 1;
const STORE_INDEX_1 = 6;
const SECOND_PLAYER_FIRST_ELEMENT = 7;
const FIRT_PLAYER_FIRST_ELEMENT = 0;


let dataBoardManager: any;
const MancalaBoard = ({data, state, props}: { data?: BoardManager, state: GameStartProps, props: GameStartProps }) => {
    const classes = useStyles();
    dataBoardManager = data;
    return (
        <div className={classes.root}>
            <Paper>
                {data && data.board && data.board.allHoles && state.playerState && state.playerState.loggedPlayer ? (
                    <Table>
                        <TableBody>
                            <TableRow>
                                <TableCell
                                    style={dataBoardManager.board.allHoles[MAX_STONE_INDEX].enabled ? holeEnabled : holeDisabled}
                                    rowSpan={STORE_ROW_SPAN} colSpan={SINGLE_SPAN}>
                                    <Button
                                        disabled={!dataBoardManager.board.allHoles[MAX_STONE_INDEX].enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                        onClick={() => swayStones(dataBoardManager.board.allHoles[MAX_STONE_INDEX].id, state, props)}
                                        href={"#"}>{data.board.allHoles[MAX_STONE_INDEX].stones}</Button></TableCell>
                                {dataBoardManager.board.allHoles.slice(SECOND_PLAYER_FIRST_ELEMENT, MAX_STONE_INDEX).reverse().map((hole: Hole) => (
                                    <TableCell
                                        style={hole.enabled ? holeEnabled : holeDisabled}
                                        rowSpan={SINGLE_SPAN} colSpan={SINGLE_SPAN}>
                                        <Button
                                            disabled={!hole.enabled || dataBoardManager.gameover ||
                                            (state.playerState && state.playerState.loggedPlayer && dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email)}
                                            onClick={() => swayStones(hole.id, state, props)}
                                            href={"#"}>{hole.stones}</Button></TableCell>
                                ))}
                                <TableCell
                                    style={dataBoardManager.board.allHoles[STORE_INDEX_1].enabled ? holeEnabled : holeDisabled}
                                    rowSpan={STORE_ROW_SPAN} colSpan={SINGLE_SPAN}>
                                    <Button
                                        disabled={!dataBoardManager.board.allHoles[STORE_INDEX_1].enabled || dataBoardManager.gameover || dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                        onClick={() => swayStones(dataBoardManager.board.allHoles[STORE_INDEX_1].id, state, props)}
                                        href={"#"}>{data.board.allHoles[STORE_INDEX_1].stones}</Button></TableCell>
                            </TableRow>
                            <TableRow>
                                {dataBoardManager.board.allHoles.slice(FIRT_PLAYER_FIRST_ELEMENT, STORE_INDEX_1).map((hole: Hole) => (
                                    <TableCell
                                        style={hole.enabled ? holeEnabled : holeDisabled}
                                        rowSpan={SINGLE_SPAN} colSpan={SINGLE_SPAN}>
                                        <Button
                                            disabled={!hole.enabled || dataBoardManager.gameover ||
                                            (state.playerState && state.playerState.loggedPlayer && dataBoardManager.currentPlayer.email !== state.playerState.loggedPlayer.email)}
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
