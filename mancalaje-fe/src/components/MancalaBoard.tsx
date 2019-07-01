import {useStyles} from "../theme";
import {Paper} from "@material-ui/core";
import Table from "@material-ui/core/Table";
import TableRow from "@material-ui/core/TableRow";
import * as React from "react";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import {BoardManager, Game} from "../types";

const MancalaBoard = ({data} : { data: BoardManager}) => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Paper>
                <Table>
                    <TableBody>
                        <TableRow>
                            <TableCell align="right" rowSpan={2}>Mancala 2</TableCell>
                            <TableCell align="right">6</TableCell>
                            <TableCell align="right">5</TableCell>
                            <TableCell align="right">4</TableCell>
                            <TableCell align="right">3</TableCell>
                            <TableCell align="right">2</TableCell>
                            <TableCell align="right">1</TableCell>
                            <TableCell align="right" rowSpan={2}>Mancala 1</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell align="right">1</TableCell>
                            <TableCell align="right">2</TableCell>
                            <TableCell align="right">3</TableCell>
                            <TableCell align="right">4</TableCell>
                            <TableCell align="right">5</TableCell>
                            <TableCell align="left">6</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </Paper>
        </div>
    );
};
export default MancalaBoard;