import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router'; 
import { NgForm } from  '@angular/forms';

import { CompanyService } from '../company.service';
import { Company } from '../company';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.css']
})
export class RegisterCompanyComponent implements OnInit 
{
  submitted: boolean;
  newCompany: Company;	
  infoMessage: string;
  errorMessage: string;
	
  constructor(private router: Router,
				private companyService: CompanyService) 
  { 
	this.submitted = false;
	this.newCompany = new Company();
  }

  ngOnInit()
  { 
  }
  
  clear()
  {
	this.submitted = false;
	this.newCompany = new Company();
  }
  
  create(registerCompanyForm: NgForm)
  {
	  this.submitted = true;
	   
	  if(registerCompanyForm.valid)
	  {
		this.companyService.registerCompany(this.newCompany).subscribe(
			reponse => {
				this.infoMessage = 'New Company registered' + reponse.newCompanyId; 
				this.errorMessage = null;
			},
			error => {
				this.infoMessage = null; 
				this.errorMessage = error;
			}
		);  
		
		//this.router.navigate(["/index"]);
	  }
  }

}
