import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http'; 
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { SessionService } from './session.service';
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

  constructor(private httpClient: HttpClient, 
    public sessionService: SessionService) 
  { 

  }

	companyLogin(email: string, password: string): Observable<any>
	{
		return this.httpClient.get<any>(this.baseUrl + "/companyLogin?email=" + email + "&password=" + password).pipe
		(
			catchError(this.handleError)
		);
  }
  
  getCompanies(): Observable<any>
  {
      return this.httpClient.get<any>(this.baseUrl).pipe
      (
        catchError(this.handleError)
      );
  }

	getCompanyByCompanyEmail(email: number): Observable<any>
	{
		return this.httpClient.get<any>(this.baseUrl + "/retrieveCompany?email=" + this.sessionService.getEmail() + "&password=" + this.sessionService.getPassword()).pipe
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
