import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { SessionService } from './session.service';
import { JobListing } from './job-listing';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class JobListingService {

	baseUrl: string = "/api/JobListing";
	
	
	constructor(private httpClient: HttpClient,
		private sessionService: SessionService)
	{		
	}


	getJobListings(): Observable<any>
	{				
		return this.httpClient.get<any>(this.baseUrl + "/retrieveAllJobListings").pipe
		(
			catchError(this.handleError)
		);
  }
  
	createNewJobListing(newJobListing: JobListing): Observable<any>
	{		
		let createProductReq = {
			"email": this.sessionService.getEmail(),
			"password": this.sessionService.getPassword(),
			"jobListingEntity": newJobListing,
			"companyId": this.sessionService.getCurrentCompany().companyId,
		};
		
		return this.httpClient.put<any>(this.baseUrl, createProductReq, httpOptions).pipe
		(
			catchError(this.handleError)
		);
	}
	
	private handleError(error: HttpErrorResponse)
	{
		let errorMessage: string = "";
		
		if (error.error instanceof ErrorEvent) 
		{		
			errorMessage = "An unknown error has occurred: " + error.error.message;
		} 
		else 
		{		
			errorMessage = "A HTTP error has occurred: " + `HTTP ${error.status}: ${error.error.message}`;
		}
		
		console.error(errorMessage);
		
		return throwError(errorMessage);		
	}
	
}
