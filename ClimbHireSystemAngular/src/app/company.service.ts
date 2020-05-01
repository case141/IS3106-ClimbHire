import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http'; 
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Company } from './company';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class CompanyService 
{
  baseUrl: string = '/api/Company';

  constructor(private httpClient: HttpClient) 
  { 

  }

  getCompany(): Observable<any>
  {
      return this.httpClient.get<any>(this.baseUrl).pipe
      (
        catchError(this.handleError)
      );
  }

  createNewCompany(newCompany: Company)
  {
      let createNewCompanyReq = {'newCompany': newCompany};
      return this.httpClient.put<any>(this.baseUrl, createNewCompanyReq, httpOptions).pipe
      (
          catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse)
  {
      let errorMessage: string = '';

      if(error.error instanceof ErrorEvent)
      {
          errorMessage = 'An unknown error has occured: ' + error.error.message;
      }
      else
      {
          errorMessage = 'An HTTP error has occurred: ' + `HTTP ${error.status}: ${error.error.message}`;
      }
      return throwError(errorMessage)
  }
}
