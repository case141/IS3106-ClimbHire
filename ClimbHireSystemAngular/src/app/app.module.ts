import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { ViewAllCompaniesComponent } from './view-all-companies/view-all-companies.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { CreateJobListingComponent } from './manageJobListing/create-job-listing/create-job-listing.component';
import { ViewAllJobListingsComponent } from './manageJobListing/view-all-job-listings/view-all-job-listings.component';
import { ViewJobListingDetailsComponent } from './manageJobListing/view-job-listing-details/view-job-listing-details.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewAllCompaniesComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    CreateJobListingComponent,
    ViewAllJobListingsComponent,
    ViewJobListingDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
