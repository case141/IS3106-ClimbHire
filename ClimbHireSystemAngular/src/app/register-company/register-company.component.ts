import { Component, OnInit } from "@angular/core";

import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";

import { CompanyService } from "../company.service";
import { Company } from "../company";
import { SubscriptionService } from "../subscription.service";
import { Subscription } from "../subscription";

import { SubscriptionStatusEnum } from "../subscription-status-enum.enum";
import { SubscriptionTypeEnum } from "../subscription-type-enum.enum";

@Component({
  selector: "app-register-company",
  templateUrl: "./register-company.component.html",
  styleUrls: ["./register-company.component.css"],
})
export class RegisterCompanyComponent implements OnInit {
  submitted: boolean;
  newCompany: Company;
  infoMessage: string;
  errorMessage: string;
  newSubscription: Subscription;

  constructor(
    private router: Router,
    private companyService: CompanyService,
    private subscriptionService: SubscriptionService
  ) {
    this.submitted = false;
    this.newCompany = new Company();
    this.newSubscription = new Subscription();
  }

  ngOnInit() {}

  clear() {
    this.submitted = false;
    this.newCompany = new Company();
    this.newSubscription = new Subscription();
  }

  create(registerCompanyForm: NgForm) {
    if (registerCompanyForm.valid) {
      this.companyService.registerCompany(this.newCompany).subscribe(
        (response) => {
          this.infoMessage = "New Company registered" + response.newCompanyId;
          this.errorMessage = null;
        },
        (error) => {
          this.infoMessage = "CANT CREATE";
          this.errorMessage = error;
        }
      );
      //this.router.navigate(["/index"]);
    }
  }

  createNew(createSubscriptionForm: NgForm) {
    this.submitted = true;
    this.newSubscription.subscriptionTypeEnum = SubscriptionTypeEnum.MONTHLY;
    this.newSubscription.subscriptionStatusEnum = SubscriptionStatusEnum.ACTIVE;
    if (createSubscriptionForm.valid) {
      this.subscriptionService
        .createNewSubscription(this.newSubscription, this.newCompany)
        .subscribe(
          (response) => {
            this.infoMessage =
              "New Subscription created" + response.subscriptionId;
            this.errorMessage = null;
          },
          (error) => {
            this.infoMessage = null;
            this.errorMessage = error;
          }
        );
      //this.router.navigate(["/index"]);
    }
  }
}