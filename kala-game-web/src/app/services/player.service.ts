import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError, retry} from "rxjs/operators";

const fetchBoardUSer = "/log/user";

@Injectable({
  providedIn: "root"
})
export class PlayerService {
  constructor(private http: HttpClient) {
  }

  public getLoggedUser() {
    return this.http.get(fetchBoardUSer, {responseType: "text"})
      .pipe(retry(3), catchError(this.handleError<string>()));

  }

  handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
