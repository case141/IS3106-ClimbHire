import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { NgbDate, NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { IndexComponent } from "./index/index.component";
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { ViewAllCompaniesComponent } from "./admin/view-all-companies/view-all-companies.component";
import { RegisterCompanyComponent } from "./register-company/register-company.component";
import { ViewProfessionalDetailsComponent } from './manageProfessionals/view-professional-details/view-professional-details.component';
import { ViewAllProfessionalsComponent } from './manageProfessionals/view-all-professionals/view-all-professionals.component';



@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewAllCompaniesComponent,
    RegisterCompanyComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    ViewProfessionalDetailsComponent,
    ViewAllProfessionalsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
