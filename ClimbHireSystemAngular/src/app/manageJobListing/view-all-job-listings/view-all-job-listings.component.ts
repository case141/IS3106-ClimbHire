import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SessionService } from '../../session.service';
import { JobListingService } from '../../job-listing.service';
import { JobListing } from '../../job-listing';

@Component({
  selector: 'app-view-all-job-listings',
  templateUrl: './view-all-job-listings.component.html',
  styleUrls: ['./view-all-job-listings.component.css']
})
export class ViewAllJobListingsComponent implements OnInit {

  jobListings: JobListing[];
  errorMessage: string;

	constructor(private jobListingService: JobListingService)
	{	  
  }
  
	ngOnInit() 
	{		
		// this.checkAccessRight();		
		
		this.jobListingService.getJobListings().subscribe(
			response => {
				this.jobListings = response.jobListingEntities;
			},
			error => {
        this.errorMessage = error 
			}
		);
	}

}
