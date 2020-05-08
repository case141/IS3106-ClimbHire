import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { NgbDate, NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { CreateJobListingComponent } from './manageJobListing/create-job-listing/create-job-listing.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';
import { ViewJobListingDetailsComponent } from './manageJobListing/view-job-listing-details/view-job-listing-details.component';
import { ViewCompanyProfileComponent } from './manageCompanyProfile/view-company-profile/view-company-profile.component';
import { ViewSubscriptionPlanComponent } from './manageSubscription/view-subscription-plan/view-subscription-plan.component';
import { ViewPaymentHistoryComponent } from './manageSubscription/view-payment-history/view-payment-history.component';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import { RegisterCompanyComponent } from './register-company/register-company.component';
import { ViewAllProfessionalsComponent } from './manageProfessionals/view-all-professionals/view-all-professionals.component';
import { ViewProfessionalDetailsComponent } from './manageProfessionals/view-professional-details/view-professional-details.component';
import { ViewAllSubscriptionsComponent } from './admin/view-all-subscriptions/view-all-subscriptions.component';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
import { ViewAllCompaniesComponent } from './admin/view-all-companies/view-all-companies.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    CreateJobListingComponent,
    ViewAllJobListingsComponent,
    ViewJobListingDetailsComponent,
    ViewCompanyProfileComponent,
    ViewSubscriptionPlanComponent,
    ViewPaymentHistoryComponent,
    BreadcrumbComponent,
    RegisterCompanyComponent,
    ViewAllProfessionalsComponent,
    ViewProfessionalDetailsComponent,
    ViewAllSubscriptionsComponent,
    AdminSidebarComponent,
    ViewAllCompaniesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
