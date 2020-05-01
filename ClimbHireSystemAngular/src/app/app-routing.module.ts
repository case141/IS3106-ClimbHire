import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { CreateNewCompanyComponent } from './create-new-company/create-new-company.component';


const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full'},
  { path: 'index', component: IndexComponent },
  { path: 'viewAllCompanies', component: ViewAllCompaniesComponent },
  { path: 'createNewCompanies', component: CreateNewCompanyComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
