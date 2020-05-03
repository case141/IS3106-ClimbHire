import { Component, OnInit, Output, EventEmitter  } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SessionService } from '../session.service';
import { CompanyService } from '../company.service';
import { Company } from '../company';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

	@Output() 
  childEvent = new EventEmitter();	
  
  email: string;
  password: string;
  loginError: boolean;
  errorMessage: string;

  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    public sessionService: SessionService,
    private companyService: CompanyService) { }

  ngOnInit() {
  }

}
