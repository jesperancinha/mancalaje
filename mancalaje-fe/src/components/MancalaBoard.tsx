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
const UNDEFINED_HOLE = -1;


const MancalaBoard = ({data, state, props}: { data?: BoardManager, state: GameStartProps, props: GameStartProps }) => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Paper>
                {
                    data && data.currentPlayer && data.board &&
                    data.board.allHoles && data.board.allHoles &&
                    state.playerState && state.playerState.loggedPlayer ? (
                        <Table>
                            <TableBody>
                                <TableRow>
                                    <TableCell
                                        style={data.board.allHoles[MAX_STONE_INDEX].player.email ===
                                        state.playerState.loggedPlayer.email ? holeEnabled : holeDisabled}
                                        rowSpan={STORE_ROW_SPAN} colSpan={SINGLE_SPAN}>
                                        key={MAX_STONE_INDEX}
                                        <Button
                                            disabled={!data.board.allHoles[MAX_STONE_INDEX].enabled ||
                                            data.gameover || data.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                            onClick={() => swayStones(data.board &&
                                            data.board.allHoles ? data.board.allHoles[MAX_STONE_INDEX].id : UNDEFINED_HOLE, state, props)}
                                            href={"#"}>{data.board.allHoles[MAX_STONE_INDEX].stones}</Button></TableCell>
                                    {data.board.allHoles.slice(SECOND_PLAYER_FIRST_ELEMENT, MAX_STONE_INDEX).reverse().map((hole: Hole) => (
                                        <TableCell
                                            key={hole.id}
                                            style={hole.enabled ? holeEnabled : holeDisabled}
                                            rowSpan={SINGLE_SPAN} colSpan={SINGLE_SPAN}>
                                            <Button
                                                disabled={!hole.enabled || data.gameover ||
                                                (state.playerState && state.playerState.loggedPlayer && data.currentPlayer &&
                                                    data.currentPlayer.email !== state.playerState.loggedPlayer.email)}
                                                onClick={() => swayStones(hole.id, state, props)}
                                                href={"#"}>{hole.stones}</Button></TableCell>
                                    ))}
                                    <TableCell
                                        key={STORE_INDEX_1}
                                        style={data.board.allHoles[STORE_INDEX_1].player.email ===
                                        state.playerState.loggedPlayer.email ? holeEnabled : holeDisabled}
                                        rowSpan={STORE_ROW_SPAN} colSpan={SINGLE_SPAN}>
                                        <Button
                                            disabled={!data.board.allHoles[STORE_INDEX_1].enabled || data.gameover ||
                                            data.currentPlayer.email !== state.playerState.loggedPlayer.email}
                                            onClick={() => swayStones(data.board &&
                                            data.board.allHoles ? data.board.allHoles[STORE_INDEX_1].id : UNDEFINED_HOLE, state, props)}
                                            href={"#"}>{data.board.allHoles[STORE_INDEX_1].stones}</Button></TableCell>
                                </TableRow>
                                <TableRow>
                                    {data.board.allHoles.slice(FIRT_PLAYER_FIRST_ELEMENT, STORE_INDEX_1).map((hole: Hole) => (
                                        <TableCell
                                            key={hole.id}
                                            style={hole.enabled ? holeEnabled : holeDisabled}
                                            rowSpan={SINGLE_SPAN} colSpan={SINGLE_SPAN}>
                                            <Button
                                                disabled={!hole.enabled || data.gameover ||
                                                (state.playerState && state.playerState.loggedPlayer && data.currentPlayer &&
                                                    data.currentPlayer.email !== state.playerState.loggedPlayer.email)}
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
