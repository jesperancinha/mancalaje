export class Board {
    name: string = '';
    allHoles: Hole[] = [];
}

class Hole {
    id: number = -1;
    stones: number = 4;
    enabled: boolean = false;
}

export class BoardManager {
    board: Board = new Board;
}

export class Game {
    boardManagers: BoardManager[] = [];
}
