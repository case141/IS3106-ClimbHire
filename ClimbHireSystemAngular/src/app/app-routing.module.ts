import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';
import { CreateJobListingComponent } from './manageJobListing/create-job-listing/create-job-listing.component';
import { ViewCompanyProfileComponent } from './manageCompanyProfile/view-company-profile/view-company-profile.component';
import { ViewSubscriptionPlanComponent } from './manageSubscription/view-subscription-plan/view-subscription-plan.component';
import { ViewPaymentHistoryComponent } from './manageSubscription/view-payment-history/view-payment-history.component';


const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full'},
  { path: 'index', component: IndexComponent },
  { path: 'viewAllCompanies', component: ViewAllCompaniesComponent },
  { path: 'viewAllJobListings', component: ViewAllJobListingsComponent },
  { path: 'createJobListing', component: CreateJobListingComponent },
  { path: 'viewCompanyProfile', component: ViewCompanyProfileComponent },
  { path: 'viewSubscriptionPlan', component: ViewSubscriptionPlanComponent },
  { path: 'viewPaymentHistory', component: ViewPaymentHistoryComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
