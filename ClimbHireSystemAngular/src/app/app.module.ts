import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { IndexComponent } from "./index/index.component";
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { MainMenuComponent } from "./main-menu/main-menu.component";
import { SidebarComponent } from "./sidebar/sidebar.component";
import { ViewAllCompaniesComponent } from "./admin/view-all-companies/view-all-companies.component";
import { RegisterCompanyComponent } from "./register-company/register-company.component";

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ViewAllCompaniesComponent,
    RegisterCompanyComponent,
    HeaderComponent,
    FooterComponent,
    MainMenuComponent,
    SidebarComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
