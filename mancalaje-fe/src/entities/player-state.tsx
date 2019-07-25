import {BoardManager} from "../model/board-manager";
import {Player} from "../model/player";

export class PlayerState {
    public boardManager: BoardManager = new BoardManager();
    public loggedPlayer?: Player;
}

