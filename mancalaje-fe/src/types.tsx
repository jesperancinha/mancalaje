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
    boardManagerId: number = -1;
}

export class Game {
    boardManagers: BoardManager[] = [];
}
