import {Pit} from "./pit";
import {Player} from "./player";

export interface Board {
  id: number;
  pitDtos: Pit[];
  pitDtoOne: Pit;
  kalahOne: Pit;
  playerDtoOne: Player;
  pitDtoTwo: Pit;
  kalahTwo: Pit;
  playerDtoTwo: Player;
  currentPlayerDto: Player;
  winnerDto: Player;
}
