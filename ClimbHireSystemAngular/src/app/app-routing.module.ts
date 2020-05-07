import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';
import { CreateJobListingComponent } from './manageJobListing/create-job-listing/create-job-listing.component';
import { ViewCompanyProfileComponent } from './manageCompanyProfile/view-company-profile/view-company-profile.component';
import { ViewSubscriptionPlanComponent } from './manageSubscription/view-subscription-plan/view-subscription-plan.component';
import { ViewPaymentHistoryComponent } from './manageSubscription/view-payment-history/view-payment-history.component';
import { ViewAllProfessionalsComponent } from './manageProfessionals/view-all-professionals/view-all-professionals.component';
import { ViewAllSubscriptionsComponent } from './admin/view-all-subscriptions/view-all-subscriptions.component';
import { ViewProfessionalDetailsComponent } from './manageProfessionals/view-professional-details/view-professional-details.component';
import { RegisterCompanyComponent } from './register-company/register-company.component';


const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full'},
  { path: 'index', component: IndexComponent },
  { path: 'viewAllCompanies', component: ViewAllCompaniesComponent },
  { path: 'viewAllJobListings', component: ViewAllJobListingsComponent },
  { path: 'createJobListing', component: CreateJobListingComponent },
  { path: 'viewCompanyProfile', component: ViewCompanyProfileComponent },
  { path: 'viewSubscriptionPlan', component: ViewSubscriptionPlanComponent },
  { path: 'viewPaymentHistory', component: ViewPaymentHistoryComponent },
  { path: 'viewAllProfessionals', component: ViewAllProfessionalsComponent },
  { path: "viewAllSubscriptions", component: ViewAllSubscriptionsComponent },
  {
    path: "manageProfessionals/viewProfessionalDetails/:productId",
    component: ViewProfessionalDetailsComponent,
  },
  { path: "registerCompany", component: RegisterCompanyComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
