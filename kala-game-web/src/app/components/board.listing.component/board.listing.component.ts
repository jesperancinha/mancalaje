import {Component, OnInit} from "@angular/core";
import {Board} from "../../model/board";
import {BoardService} from "../../services/board.service";
import {PlayerService} from "../../services/player.service";
import {Pit} from "../../model/pit";
import {Player} from "../../model/player";

@Component({
  selector: 'board-listing',
  templateUrl: './board.listing.component.html',
  styleUrls: ['./board.listing.component.scss'],
  standalone: false
})
export class BoardListingComponent implements OnInit {
  allPlayerBoards: Board[];
  allAvailableBoards: Board[];
  loggedUser: string;
  currentBoardId: number;
  currentBoard: Board;
  currentPlayerOnePits: Pit[];
  currentPlayerTwoPits: Pit[];
  currentPlayerOneKalahPit: Pit;
  currentPlayerTwoKalahPit: Pit;

  constructor(
    private boardService: BoardService,
    private playerService: PlayerService
  ) {
  }

  ngOnInit(): void {
    this.refresh();
  }

  createNewBoard() {
    this.boardService.createBoard().subscribe(
      () => {
        this.refresh();
      })
  }

  private refresh() {
    this.boardService.getCurrentBoard()
      .subscribe(data => {
        if (data) {
          this.currentBoard = data;
          this.setCurrentBoard(data);
        } else {
          this.currentBoard = null;
          this.currentBoardId = null;
          this.boardService.getAllPlayerBoards()
            .subscribe(data => {
              this.allPlayerBoards = data;
              if (this.allPlayerBoards && this.allPlayerBoards.length > 0) {
                this.setCurrentBoard(this.allPlayerBoards[0]);
              }
            });

          this.boardService.getAllAvailableBoards()
            .subscribe(data => {
              this.allAvailableBoards = data;
            })
        }
      })


    this.playerService.getLoggedUser()
      .subscribe(data => {
        console.log(data)
        if (!data) {
          window.location.href = "login";
        }
        this.loggedUser = data;
      })
    setTimeout(() => this.refresh(), 1000);
  }

  private setCurrentBoard(board: Board) {
    this.currentBoardId = board.id;
    this.currentBoard = board;
    this.currentPlayerOnePits = [];
    this.currentPlayerTwoPits = [];
    let allPits = board.pitDtos;
    let pit = allPits[0]
    let i = 0
    while (pit.pitType === 'SMALL') {
      this.currentPlayerOnePits.push(pit);
      pit = allPits[++i];
    }
    this.currentPlayerOneKalahPit = pit;
    pit = allPits[++i];
    while (pit.pitType === 'SMALL') {
      this.currentPlayerTwoPits.push(pit);
      pit = allPits[++i];
    }
    this.currentPlayerTwoKalahPit = pit;
  }

  joinBoard(id: number) {
    this.boardService.joinBoard(id)
      .subscribe(data => {
        this.currentBoard = data;
      })
  }

  moveStone(id: number) {
    this.boardService.move(this.currentBoard.id, id)
      .subscribe(data => {
        this.currentBoard = data;
        this.setCurrentBoard(data);
      })
  }

  checkTurn(playerDto: Player, pit: Pit) {
    if (!playerDto) {
      return "disabled";
    }
    if (this.currentBoard.winnerDto) {
      return "disabled";
    }
    if (pit.stones == 0) {
      return "disabled";
    }
    if (playerDto.username === this.currentBoard.currentPlayerDto.username
      && this.loggedUser === this.currentBoard.currentPlayerDto.username) {
      return "";
    }
    return "disabled";
  }

  leaveGame() {
    this.boardService.leaveGame()
      .subscribe();
  }
}
