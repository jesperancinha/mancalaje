import {Board} from "./board";
import {Player} from "./player";

export class BoardManager {
    public board?: Board;
    public boardManagerId: Number = -1;
    public owner?: Player;
    public currentPlayer?: Player;
    public gameover?: boolean;
    public winner?: Player;

}
