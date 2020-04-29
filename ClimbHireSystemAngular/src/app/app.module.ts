import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';
import { ViewJobListingDetailsComponent } from './manageJobListing/view-job-listing-details/view-job-listing-details.component';
import { ViewCandidateProfileComponent } from './manageJobListing/view-candidate-profile/view-candidate-profile.component';
import { ViewCompanyProfileComponent } from './manageSubscription/view-company-profile/view-company-profile.component';
import { ViewSubscriptionPlanComponent } from './manageSubscription/view-subscription-plan/view-subscription-plan.component';
import { ViewPaymentHistoryComponent } from './manageSubscription/view-payment-history/view-payment-history.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    ViewAllJobListingsComponent,
    ViewJobListingDetailsComponent,
    ViewCandidateProfileComponent,
    ViewCompanyProfileComponent,
    ViewSubscriptionPlanComponent,
    ViewPaymentHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
