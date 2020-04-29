import { Company } from './company';

export class Payment 
{
	paymentId: number;
	amountPaid: number;
	status: string;
	company: Company;
	paymentDate: Date;
	
	constructor(paymentId?: number, amountPaid?: number, status?: string,company?: Company, paymentDate?: Date)
	{
		this.paymentId = paymentId;
		this.amountPaid = amountPaid;
		this.status = status;
		this.company = company;
		this.paymentDate = paymentDate;
	}
}
