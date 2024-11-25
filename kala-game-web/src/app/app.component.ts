import {Component} from '@angular/core';
import {catchError, retry} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, of} from "rxjs";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: false
})
export class AppComponent {
  title = "kala-game-web";

  constructor(private http: HttpClient, private router: Router) {
  }

  logout() {
    this.http.post("/logout", {}).pipe(
      retry(3), catchError(this.handleError<string>())).subscribe(() => {
        window.location.href = "http://localhost:4200/login";
      }
    );
  }

  handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
