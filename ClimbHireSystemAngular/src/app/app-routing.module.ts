import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';


const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full'},
  { path: 'index', component: IndexComponent },
  { path: 'viewAllCompanies', component: ViewAllCompaniesComponent },
  { path: 'viewAllJobListings', component: ViewAllJobListingsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
