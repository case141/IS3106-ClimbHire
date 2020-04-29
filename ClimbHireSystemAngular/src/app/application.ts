import { Professional } from './professional';
import { JobListing } from './job-listing';

export class Application {
	
	applicationId: number;
	applicationStatus: string;
	applicationCreatedDate: Date;
	createdBy: Professional;
	createdFor: JobListing;
	
	constructor(applicationId?: number, applicationStatus?: string, applicationCreatedDate?: Date, createdBy?: Professional, createdFor?: JobListing)
	{
		this.applicationId = applicationId;
		this.applicationStatus = applicationStatus;
        this.applicationCreatedDate = applicationCreatedDate;
        this.createdBy = createdBy;
        this.createdFor = createdFor;
	}
}
