import {Board} from "./board";

export interface Player {
  id: number;
  username: string;
  boardDtos: Board[];
  opponent: Player;
}
