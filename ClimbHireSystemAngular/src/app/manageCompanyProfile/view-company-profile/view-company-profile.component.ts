import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SessionService } from '../../session.service';
import { CompanyService } from '../../company.service';
import { Company } from '../../company';


@Component({
  selector: 'app-view-company-profile',
  templateUrl: './view-company-profile.component.html',
  styleUrls: ['./view-company-profile.component.css']
})
export class ViewCompanyProfileComponent implements OnInit {

	email: number;
	companyToUpdate: Company;
  	retrieveCompanyError: boolean;
  
	constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    public sessionService: SessionService,
    private companyService: CompanyService)
  {
  this.retrieveCompanyError = false;
  }


  ngOnInit() {
		this.email = parseInt(this.activatedRoute.snapshot.paramMap.get('email'));
		
		this.companyService.getCompanyByCompanyEmail(this.email).subscribe(
			response => {
				this.companyToUpdate = response.productEntity;
			},
			error => {
				this.retrieveCompanyError = true;
				console.log('********** ViewCompanyProfileComponent.ts: ' + error);
			}
		);
  }

}
