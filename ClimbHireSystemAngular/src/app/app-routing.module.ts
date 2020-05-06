import { NgModule, Component } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { IndexComponent } from "./index/index.component";
import { ViewAllCompaniesComponent } from "./admin/view-all-companies/view-all-companies.component";
import { RegisterCompanyComponent } from "./register-company/register-company.component";
import { ViewAllProfessionalsComponent } from './manageProfessionals/view-all-professionals/view-all-professionals.component';

const routes: Routes = [
  { path: "", redirectTo: "/index", pathMatch: "full" },
  { path: "index", component: IndexComponent },
  { path: "viewAllCompanies", component: ViewAllCompaniesComponent },
  { path: "registerCompany", component: RegisterCompanyComponent },
  { path: "viewAllProfessionals", component: ViewAllProfessionalsComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
