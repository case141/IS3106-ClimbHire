import { Application } from './application';
import { Company } from './company';

export class JobListing 
{
	jobListingId: number;
	jobTitle: string;
	workLocation: string;
	datePosted: Date;
	basicMonthlyPay: number;
	payPerHour: number;
	responsibilities: string;
	qualifications: string[];
	skillsRequired: string[];
	contract: string;
	status: string;
	numOfPositionAvailable: number;
	company: Company;
	applicationList: Application[];
	
	constructor(jobListingId?: number, jobTitle?: string, workLocation?: string, datePosted?: Date, basicMonthlyPay?: number, payPerHour?: number,
				responsibilities?: string, contract?: string, status?: string, numOfPositionAvailable?: number, company?: Company)
	{
		this.jobListingId = jobListingId;
        this.jobTitle = jobTitle;
        this.workLocation = workLocation;
        this.datePosted = datePosted;
        this.basicMonthlyPay = basicMonthlyPay;
        this.payPerHour = payPerHour;
        this.responsibilities = responsibilities;
        this.contract = contract;
        this.status = status;
        this.numOfPositionAvailable = numOfPositionAvailable;
        this.company = company;
	}
}
