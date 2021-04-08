import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {Board} from "../model/board";


const createBoardUrl = "/api/create"
const getPlayerBoarsdUrl = "/api"
const getAvailableBoarsdUrl = "/api/available"

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  constructor(private http: HttpClient) {
  }

  public createBoard() {
    return this.http.post(createBoardUrl, {}).pipe(
      retry(3), catchError(this.handleError()));
  }

  public getSelectedBoard(id: number) {
    return this.http.get<Board[]>(getPlayerBoarsdUrl + "/" + id)
      .pipe(retry(3), catchError(this.handleError<Board[]>()));

  }

  public getAllPlayerBoards() {
    return this.http.get<Board[]>(getPlayerBoarsdUrl)
      .pipe(retry(3), catchError(this.handleError<Board[]>()));
  }

  public getAllAvailableBoards() {
    return this.http.get<Board[]>(getAvailableBoarsdUrl)
      .pipe(retry(3), catchError(this.handleError<Board[]>()));
  }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
