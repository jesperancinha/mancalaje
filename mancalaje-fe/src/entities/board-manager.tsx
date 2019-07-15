import {Board} from "./board";
import {Player} from "./player";

export class BoardManager {
    board?: Board;
    boardManagerId: number = - 1;
    owner?: Player;
    currentPlayer?: Player;
    gameover?: boolean;
    winner?: Player;
}