import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SessionService } from '../../session.service';
import { PaymentService } from '../../payment.service';
import { Payment } from '../../payment';

@Component({
  selector: 'app-view-payment-history',
  templateUrl: './view-payment-history.component.html',
  styleUrls: ['./view-payment-history.component.css']
})
export class ViewPaymentHistoryComponent implements OnInit {

  paymentHistory: Payment[];
  errorMessage: string;

	constructor(private paymentService: PaymentService)
	{	  
  }
  
	ngOnInit() 
	{		
		this.paymentService.getPaymentHistory().subscribe(
			response => {
				this.paymentHistory = response.paymentList;
			},
			error => {
        this.errorMessage = error 
			}
		);
	}

}
