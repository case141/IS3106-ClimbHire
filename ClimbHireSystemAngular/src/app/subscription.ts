import { Company } from './company';
import { SubscriptionTypeEnum } from './subscription-type-enum.enum';
import { SubscriptionStatusEnum } from './subscription-status-enum.enum';


export class Subscription 
{
	subscriptionId: number;
	subscriptionTypeEnum: SubscriptionTypeEnum;
	description: string;
	amount: number;
	statusEnum: SubscriptionStatusEnum;
	renewalDate: Date;
	company: Company;
	
	constructor(subscriptionId?: number, subscriptionTypeEnum?: SubscriptionTypeEnum, description?: string, amount?: number, statusEnum?: SubscriptionStatusEnum,
				renewalDate?: Date, company?: Company)
	{
		this.subscriptionId = subscriptionId;
		this.subscriptionTypeEnum = subscriptionTypeEnum;
		this.description = description;
		this.amount = amount;
		this.statusEnum = statusEnum;
		this.renewalDate = renewalDate;
		this.company = company;
	}
}
