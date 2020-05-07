import { SubscriptionStatusEnum } from "./subscription-status-enum.enum";
import { SubscriptionTypeEnum } from "./subscription-type-enum.enum";

export class Subscription {
  subscriptionId: number;
  subscriptionTypeEnum: SubscriptionTypeEnum;
  description: string;
  amount: number;
  subscriptionStatusEnum: SubscriptionStatusEnum;
  renewalDate: any;

  constructor(
    subscriptionId?: number,
    subscriptionTypeEnum?: SubscriptionTypeEnum,
    description?: string,
    amount?: number,
    subscriptionStatusEnum?: SubscriptionStatusEnum,
    renewalDate?: any
  ) {
    this.subscriptionId = subscriptionId;
    this.subscriptionTypeEnum = subscriptionTypeEnum;
    this.description = description;
    this.amount = amount;
    this.subscriptionStatusEnum = subscriptionStatusEnum;
    this.renewalDate = renewalDate;
  }
}