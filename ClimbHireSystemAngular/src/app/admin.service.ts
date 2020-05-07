import { Injectable } from "@angular/core";

import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from "@angular/common/http";

import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

import { Admin } from "./admin";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
};

@Injectable({
  providedIn: "root",
})
export class AdminService {
  baseUrl: string = "/api/Admin";

  constructor(private httpClient: HttpClient) {}

  adminLogin(email: string, password: string): Observable<any> {
    return this.httpClient
      .get<any>(
        this.baseUrl + "/adminLogin?email=" + email + "&password=" + password
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