import {Player} from "./player";

export class Hole {
    public id = -1;
    public stones?: number;
    public enabled?: boolean;
    public player: Player = new Player();
}
