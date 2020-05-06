import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SessionService } from '../../session.service';
import { JobListingService } from '../../job-listing.service';
import { JobListing } from '../../job-listing';
import { JobListingStatusEnum } from 'src/app/job-listing-status-enum.enum';

@Component({
  selector: 'app-create-job-listing',
  templateUrl: './create-job-listing.component.html',
  styleUrls: ['./create-job-listing.component.css']
})
export class CreateJobListingComponent implements OnInit {

	submitted: boolean;
  newJobListing: JobListing;
  
	resultSuccess: boolean;
	resultError: boolean;
	message: string;
	
	constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    public sessionService: SessionService,
    private jobListingService: JobListingService)
  {
  this.submitted = false;
  this.newJobListing = new JobListing();
  this.newJobListing.jobListingStatusEnum = JobListingStatusEnum.OPEN;

  this.resultSuccess = false;
  this.resultError = false;
  }

  ngOnInit() {
  }

	
	clear()
	{
		this.submitted = false;
		this.newJobListing = new JobListing();
  }
  
	create(createJobListingForm: NgForm)
	{	
	
		this.submitted = true;
		
		if (createJobListingForm.valid) 
		{
			this.jobListingService.createNewJobListing(this.newJobListing).subscribe(
				response => {
					let newJobListingId: number = response.jobListingId;
					this.resultSuccess = true;
					this.resultError = false;
					this.message = "New Job Listing " + newJobListingId + " created successfully";
				},
				error => {
					this.resultError = true;
					this.resultSuccess = false;
					this.message = "An error has occurred while creating the new job listing: " + error;
					
					console.log('********** CreateNewJobListingComponent.ts: ' + error);
				}
			);
		}
	}
	
}
