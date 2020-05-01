import { Injectable } from '@angular/core';

import { Company } from './company';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() 
  { 

  }
  getCompanies(): Company[]
  {
      try
      {
          return JSON.parse(sessionStorage.companies);
      }
      catch
      {
          return null;
      }
  }
  setCompanies(companies: Company[]): void
  {
      sessionStorage.companies = JSON.stringify(companies);
  }
  
}
