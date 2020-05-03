import { Injectable } from '@angular/core';

import { Company } from './company';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor() 
  { 

  }

  getIsLogin(): boolean
  {
		if(sessionStorage.isLogin == "true")
		{
			return true;
		}
		else
		{
			return false;
		}
  }

	setIsLogin(isLogin: boolean): void
	{
		sessionStorage.isLogin = isLogin;
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
  
	
	getEmail(): string
	{
		return sessionStorage.email;
	}



	setEmail(email: string): void
	{
		sessionStorage.email = email;
	}
	
	getPassword(): string
	{
		return sessionStorage.password;
	}



	setPassword(password: string): void
	{
		sessionStorage.password = password;
	}
	
}
