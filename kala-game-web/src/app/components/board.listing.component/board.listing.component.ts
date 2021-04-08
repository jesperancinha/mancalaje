import {Component, OnInit} from "@angular/core";
import {Board} from "../../model/board";
import {BoardService} from "../../services/board.service";

@Component({
  selector: 'board-listing',
  templateUrl: './board.listing.component.html',
  styleUrls: ['./board.listing.component.scss']
})
export class BoardListingComponent implements OnInit {
  constructor(private boardService: BoardService) {
  }

  ngOnInit(): void {
  }
}
