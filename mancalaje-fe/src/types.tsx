export class PlayerState {
    boardManager?: BoardManager;
    loggedPlayer?: Player;
}


export class Player {
    name?: string;
    opponent?: Player;
}

export class Board {
    name?: string;
    allHoles?: Hole[];
    player1?: Player;
    player2?: Player;
}

class Hole {
    id: number = -1;
    stones: number = 4;
    enabled: boolean = false;
}

export class BoardManager {
    board: Board = new Board();
    boardManagerId: number = -1;
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