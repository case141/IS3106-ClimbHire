import { Component, OnInit } from "@angular/core";

import { CompanyService } from "../../company.service";
import { Company } from "../../company";
import { Subscription } from "../../subscription";
import { SubscriptionService } from "src/app/subscription.service";

@Component({
  selector: "app-view-all-companies",
  templateUrl: "./view-all-companies.component.html",
  styleUrls: ["./view-all-companies.component.css"],
})
export class ViewAllCompaniesComponent implements OnInit {
  companies: Company[];
  errorMessage: string;
  subscriptions: Subscription[];

  constructor(
    private companyService: CompanyService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit() {
    this.companyService.getCompanies().subscribe(
      (response) => {
        this.companies = response.companies;
      },
      (error) => {
        this.errorMessage = error;
      }
    );
  }
}
