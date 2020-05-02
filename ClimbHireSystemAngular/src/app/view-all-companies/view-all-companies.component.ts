import { Component, OnInit } from '@angular/core';

import { CompanyService } from '../company.service';
import { Company } from '../company';

@Component({
  selector: 'app-view-all-companies',
  templateUrl: './view-all-companies.component.html',
  styleUrls: ['./view-all-companies.component.css']
})
export class ViewAllCompaniesComponent implements OnInit {

  companies: Company[];
  errorMessage: string;

  constructor(private companyService: CompanyService) { }

  ngOnInit() {
    this.companyService.getCompanies().subscribe(
      response => {
        this.companies=response.companies
      },
      error => { 
        this.errorMessage = error 
      }
    );
  }

}
