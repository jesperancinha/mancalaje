import {Component, OnInit} from "@angular/core";
import {Board} from "../../model/board";
import {BoardService} from "../../services/board.service";

@Component({
  selector: 'board-listing',
  templateUrl: './board.listing.component.html',
  styleUrls: ['./board.listing.component.scss']
})
export class BoardListingComponent implements OnInit {
  allPlayerBoards: Board[];
  allAvailableBoars: Board[];

  constructor(private boardService: BoardService) {
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
    this.boardService.getAllPlayerBoards()
      .subscribe(data => {
        this.allPlayerBoards = data;
      });
    this.boardService.getAllAvailableBoards()
      .subscribe(data => {
        this.allAvailableBoars = data;
      })
  }

}
