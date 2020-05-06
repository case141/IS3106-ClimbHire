import { Injectable } from "@angular/core";

import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from "@angular/common/http";

import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

import { SessionService } from "./session.service";
import { Professional } from "./professional";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
};

@Injectable({
  providedIn: "root",
})
export class ProfessionalService {
  baseUrl: string = "/api/Professional";

  constructor(
    private httpClient: HttpClient,
    private sessionService: SessionService
  ) {}

  getProfessionals(): Observable<any> {
    return this.httpClient
      .get<any>(this.baseUrl + "/retrieveAllProfessionals")
      .pipe(catchError(this.handleError));
  }

  retrieveProfessionalById(professionalId: number): Observable<any> {
    return this.httpClient
      .get<any>(
        this.baseUrl +
          "/retrieveProfessional/" +
          professionalId +
          "?email=" +
          this.sessionService.getEmail() +
          "&password=" +
          this.sessionService.getPassword()
      )
      .pipe(catchError(this.handleError));
  }

  deleteProduct(professionalId: number): Observable<any> {
    return this.httpClient
      .delete<any>(
        this.baseUrl +
          "/" +
          professionalId +
          "?username=" +
          this.sessionService.getEmail() +
          "&password=" +
          this.sessionService.getPassword()
      )
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage: string = "";

    if (error.error instanceof ErrorEvent) {
      errorMessage = "An unknown error has occurred: " + error.error.message;
    } else {
      errorMessage =
        "A HTTP error has occurred: " +
        `HTTP ${error.status}: ${error.error.message}`;
    }

    return throwError(errorMessage);
  }
}
