import { Injectable } from "@angular/core";
import { Company } from "./company";
import { Admin } from "./admin";
import { Subscription } from "./subscription";

@Injectable({
  providedIn: "root",
})
export class SessionService {
  constructor() {}

  getCompanies(): Company[] {
    try {
      return JSON.parse(sessionStorage.companies);
    } catch {
      return null;
    }
  }

  getSubscriptions(): Subscription[] {
    try {
      return JSON.parse(sessionStorage.subscriptions);
    } catch {
      return null;
    }
  }

  setCompanies(companies: Company[]): void {
    sessionStorage.companies = JSON.stringify(companies);
  }

  getIsLogin(): boolean {
    if (sessionStorage.isLogin == "true") {
      return true;
    } else {
      return false;
    }
  }

  setIsLogin(isLogin: boolean): void {
    sessionStorage.isLogin = isLogin;
  }

  getCurrentCompany(): Company {
    try {
      return JSON.parse(sessionStorage.currentCompany);
    } catch {
      return null;
    }
  }
  setCurrentCompany(currentCompany: Company): void {
    sessionStorage.currentCompany = JSON.stringify(currentCompany);
  }

  getCurrentAdmin(): Company {
    try {
      return JSON.parse(sessionStorage.currentAdmin);
    } catch {
      return null;
    }
  }

  setCurrentAdmin(currentAdmin: Admin): void {
    sessionStorage.currentAdmin = JSON.stringify(currentAdmin);
  }

  getCompanyName(): string {
    return sessionStorage.companyName;
  }

  getEmail(): string {
    return sessionStorage.email;
  }

  getProfessionalEmail(): string {
    return sessionStorage.email;
  }

  setProfessionalEmail(email: string): void {
    sessionStorage.email = email;
  }

  getProfessionalPassword(): string {
    return sessionStorage.password;
  }

  setProfessionalPassword(password: string): void {
    sessionStorage.password = password;
  }

  setEmail(email: string): void {
    sessionStorage.email = email;
  }

  getPassword(): string {
    return sessionStorage.password;
  }

  setPassword(password: string): void {
    sessionStorage.password = password;
  }
}