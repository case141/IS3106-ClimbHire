import { Subscription } from './subscription';
import { Payment } from './payment';

export class Company 
{
	companyId: number;
	companyName: string;
	password: string;
	email: string;
	contactNumber: number;
	companyBio: string;
	dateOfFounding: Date;
	dateJoined: Date;
	paymentHistory: Payment[];
	subscription: Subscription;
	
	constructor(companyId?: number, companyName?: string, password?: string, email?: string, 
				contactNumber?: number, companyBio?: string, dateOfFounding?: Date, dateJoined?: Date)
	{
		this.companyId = companyId;
		this.companyName = companyName;
		this.password = password;
		this.email = email;
		this.contactNumber = contactNumber;
		this.companyBio = companyBio;
		this.dateOfFounding = dateOfFounding;
		this.dateJoined = dateJoined;
	}
}
