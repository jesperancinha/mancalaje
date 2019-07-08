import {useStyles} from "../theme";
import {Paper} from "@material-ui/core";
import * as React from "react";
import {BoardManager} from "../types";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import logo from "../home/logo.svg";
import "./MancalaBoard.css"

const MancalaBoard = ({data}: { data: BoardManager }) => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Paper>
                {data.board ? (
                    <Table>
                        <TableBody>
                            <TableRow>
                                <TableCell align="right" rowSpan={2}>{data.board.allHoles[13].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[12].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[11].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[10].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[9].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[8].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[7].stones}</TableCell>
                                <TableCell align="right" rowSpan={2}>{data.board.allHoles[6].stones}</TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell align="right">{data.board.allHoles[0].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[1].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[2].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[3].stones}</TableCell>
                                <TableCell align="right">{data.board.allHoles[4].stones}</TableCell>
                                <TableCell align="left"> {data.board.allHoles[5].stones}</TableCell>
                            </TableRow>
                        </TableBody>
                    </Table>
                ) : (<h1>Loading data...<img src={logo} className="App-logo-loading" alt="logo"/></h1>)}
            </Paper>
        </div>
    );
};
export default MancalaBoard;