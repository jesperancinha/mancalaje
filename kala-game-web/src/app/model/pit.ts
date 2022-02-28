import {Player} from "./player";

export interface Pit {
  id: number;
  pitType: string;
  stones: number;
  nextPitDto: Pit;
  oppositePitDto: Pit;
  playerDto: Player
}
