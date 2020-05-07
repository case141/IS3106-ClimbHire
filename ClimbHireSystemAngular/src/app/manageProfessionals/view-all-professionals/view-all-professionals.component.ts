import { Component, OnInit } from '@angular/core';

import { ProfessionalService } from '../../professional.service';
import { Professional } from '../../professional';

@Component({
  selector: 'app-view-all-professionals',
  templateUrl: './view-all-professionals.component.html',
  styleUrls: ['./view-all-professionals.component.css']
})
export class ViewAllProfessionalsComponent implements OnInit {

  professionals: Professional[];
  errorMessage: string;


  constructor(private professionalService: ProfessionalService) { }

  ngOnInit() {
    this.professionalService.getProfessionals().subscribe(
      response => {
        this.professionals = response.professionals
      },
      error => {
        this.errorMessage = error
      }
    );
  }

}