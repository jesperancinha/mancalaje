import {Hole} from "./hole";
import {Player} from "./player";

export class Board {
    public name?: string;
    public allHoles?: Hole[];
    public player1?: Player;
    public player2?: Player;
    public winner?: Player;
}
