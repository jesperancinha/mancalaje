import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {Board} from "../model/board";


const createBoardUrl = "/api/create";
const getPlayerBoarsdUrl = "/api/current";
const getAvailableBoarsdUrl = "/api/available";
const joinBoardUrl = "/api/join";
const moveUrl = "/api/move";
const leaveUrl = "/api/leave";

@Injectable({
  providedIn: "root"
})
export class BoardService {
  constructor(private http: HttpClient) {
  }

  public createBoard() {
    return this.http.post(createBoardUrl, "").pipe(
      retry(3), catchError(this.handleError()));
  }

  public getCurrentBoard() {
    return this.http.get<Board>(getPlayerBoarsdUrl)
      .pipe(retry(3), catchError(this.handleError<Board>()));

  }

  public getAllPlayerBoards() {
    return this.http.get<Board[]>(getPlayerBoarsdUrl)
      .pipe(retry(3), catchError(this.handleError<Board[]>()));
  }

  public getAllAvailableBoards() {
    return this.http.get<Board[]>(getAvailableBoarsdUrl)
      .pipe(retry(3), catchError(this.handleError<Board[]>()));
  }

  public joinBoard(id: number) {
    return this.http.put<Board>(joinBoardUrl + "/" + id, {})
      .pipe(retry(3), catchError(this.handleError<Board>()));
  }

  public move(boardId: number, pitId: number) {
    return this.http.put<Board>(moveUrl + "/" + boardId + "/" + pitId, {})
      .pipe(retry(3), catchError(this.handleError<Board>()));
  }

  leaveGame() {
    return this.http.put(leaveUrl, {})
      .pipe(retry(3), catchError(this.handleError()));
  }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
