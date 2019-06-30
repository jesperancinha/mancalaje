
export class Board {
    name: string = '';
}

export class BoardManager {
    board: Board = new Board;
}

export class Game {
    boardManagers: BoardManager[] = [];
}
