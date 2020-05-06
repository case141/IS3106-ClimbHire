import { JobListingStatusEnum } from './job-listing-status-enum.enum';
import { Application } from './application';

export class JobListing {
	jobListingId: number;
	jobTitle: string;
	workLocation: string;
	basicMonthlyPay: number;
	payPerHour: number;
	responsibilities: string;
	qualifications: string[];
	skillsRequired: string[];
	contract: string;
    jobListingStatusEnum: JobListingStatusEnum;
    numOfPositionAvailable: number;
    applicationList: Application[];
    datePosted: any;
    expiryDate: any;

    constructor(jobListingId?: number, jobTitle?: string, workLocation?: string, basicMonthlyPay?: number,
                payPerHour?: number, responsibilities?: string, contract?: string, jobListingStatusEnum?: JobListingStatusEnum,
                numOfPositionAvailable?: number, datePosted?: any, expiryDate?: any)
    { 
        this.jobListingId = jobListingId;
        this.jobTitle = jobTitle;
        this.workLocation = workLocation;
        this.basicMonthlyPay = basicMonthlyPay;
        this.payPerHour = payPerHour;
        this.responsibilities = responsibilities;
        this.contract = contract;
        this.jobListingStatusEnum = jobListingStatusEnum;
        this.numOfPositionAvailable = numOfPositionAvailable;
        this.datePosted = datePosted;
        this.expiryDate = expiryDate;

    }
}
