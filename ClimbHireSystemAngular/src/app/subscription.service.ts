import { Injectable } from "@angular/core";

import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from "@angular/common/http";

import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

import { Company } from "./company";
import { Subscription } from "./subscription";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
};

@Injectable({
  providedIn: "root",
})
export class SubscriptionService {
  baseUrl: string = "/api/Subscription";

  constructor(private httpClient: HttpClient) {}

  getSubscriptions(): Observable<any> {
    return this.httpClient
      .get<any>(this.baseUrl + "/retrieveAllSubscriptions")
      .pipe(catchError(this.handleError));
  }

  createNewSubscription(
    newSubscription: Subscription,
    newCompany: Company
  ): Observable<any> {
    let createNewSubscriptionReq = {
      newSubscription: newSubscription,
      newCompany: Company,
    };

    return this.httpClient
      .put<any>(
        this.baseUrl + "/createNewSubscription",
        createNewSubscriptionReq,
        httpOptions
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
