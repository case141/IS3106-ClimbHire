import { PaymentStatusEnum } from './payment-status-enum.enum';

export class Payment {
    paymentId: number;
    amountPaid: number;
    datePaid: any; 
    status: PaymentStatusEnum;

    constructor(paymentId?: number, amountPaid?: number, datePaid?: any, status?: PaymentStatusEnum)
    {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.datePaid = datePaid;
        this.status = status
    }
    
}
