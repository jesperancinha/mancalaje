export class PlayerState {
    boardManager?: BoardManager;
    loggedPlayer?: Player;
}


export class Player {
    name?: string;
    opponentName?: String;
}

export class Board {
    name?: string;
    allHoles?: Hole[];
    player1?: Player;
    player2?: Player;
}

class Hole {
    id?: number;
    stones?: number;
    enabled?: boolean;
}

export class BoardManager {
    board: Board = new Board();
    boardManagerId: number = -1;
    owner: Player = new Player();
}

export class Game {
    boardManagers: BoardManager[] = [];
}


export class User {
    username?: string;
    password?: string;
}

export class MancalaState {
    oauth: any = null;
}

export class ErrorMessage {
    localizedMessage?: string;
}