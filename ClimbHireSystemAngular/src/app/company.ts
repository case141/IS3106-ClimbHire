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
	
	constructor(companyId?: number, companyName?: string, password?: string, email?: string, 
				contactNumber?: number, companyBio?: string, dateOfFounding?: Date, dateJoined?: Date)
	{
		this.companyId = companyId;
	}
}
