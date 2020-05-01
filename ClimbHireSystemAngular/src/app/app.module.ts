import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { CreateNewCompanyComponent } from './create-new-company/create-new-company.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewAllCompaniesComponent,
    CreateNewCompanyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
