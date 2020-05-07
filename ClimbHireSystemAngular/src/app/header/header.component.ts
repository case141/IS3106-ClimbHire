import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

import { SessionService } from "../session.service";
import { CompanyService } from "../company.service";
import { AdminService } from "../admin.service";
import { Company } from "../company";
import { Admin } from "../admin";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent implements OnInit {
  @Output()
  childEvent = new EventEmitter();

  email: string;
  password: string;
  loginError: boolean;
  errorMessage: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public sessionService: SessionService,
    private companyService: CompanyService,
    private adminService: AdminService
  ) {
    this.loginError = false;
  }

  ngOnInit() {}

  companyLogin(): void {
    this.sessionService.setEmail(this.email);
    this.sessionService.setPassword(this.password);

    this.companyService.companyLogin(this.email, this.password).subscribe(
      (response) => {
        let company: Company = response.companyEntity;

        if (company != null) {
          this.sessionService.setIsLogin(true);
          this.sessionService.setCurrentCompany(company);
          this.loginError = false;

          this.childEvent.emit();

          this.router.navigate(["/viewAllJobListings"]);
        } else {
          this.loginError = true;
        }
      },
      (error) => {
        this.loginError = true;
        this.errorMessage = error;
      }
    );
  }

  adminLogin(): void {
    this.sessionService.setEmail(this.email);
    this.sessionService.setPassword(this.password);

    this.adminService.adminLogin(this.email, this.password).subscribe(
      (response) => {
        let admin: Admin = response.adminEntity;

        if (admin != null) {
          this.sessionService.setIsLogin(true);
          this.sessionService.setCurrentAdmin(admin);
          this.loginError = false;

          this.childEvent.emit();

          this.router.navigate(["/viewAllCompanies"]);
        } else {
          this.loginError = true;
        }
      },
      (error) => {
        this.loginError = true;
        this.errorMessage = error;
      }
    );
  }

  companyLogout(): void {
    this.sessionService.setIsLogin(false);
    this.sessionService.setCurrentCompany(null);

    this.router.navigate(["/index"]);
  }

  adminLogout(): void {
    this.sessionService.setIsLogin(false);
    this.sessionService.setCurrentAdmin(null);

    this.router.navigate(["/index"]);
  }
}
