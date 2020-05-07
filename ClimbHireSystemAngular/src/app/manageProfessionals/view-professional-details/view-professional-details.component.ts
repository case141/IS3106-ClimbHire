import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

import { SessionService } from "../../session.service";
import { ProfessionalService } from "../../professional.service";
import { Professional } from "../../professional";

@Component({
  selector: "app-view-professional-details",
  templateUrl: "./view-professional-details.component.html",
  styleUrls: ["./view-professional-details.component.css"],
})
export class ViewProfessionalDetailsComponent implements OnInit {
  userId: number;
  ProfessionalToDelete: Professional;
  error: boolean;
  errorMessage: String;
  professionalToView: Professional;
  retrieveProfessionalError: boolean;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    public sessionService: SessionService,
    private professionalService: ProfessionalService
  ) {
    this.retrieveProfessionalError = false;
    this.error = false;
  }

  ngOnInit() {
    this.userId = parseInt(this.activatedRoute.snapshot.paramMap.get("userId"));

    this.professionalService.retrieveProfessionalById(this.userId).subscribe(
      (response) => {
        this.professionalToView = response.professionalEntity;
      },
      (error) => {
        this.retrieveProfessionalError = true;
        console.log(
          "********** ViewProfessionalsDetailsComponent.ts: " + error
        );
      }
    );
  }

  deleteProfessional() {
    this.professionalService.deleteProfessional(this.userId).subscribe(
      (response) => {
        this.router.navigate(["/manageProfessionals/viewAllProfessionals"]);
      },
      (error) => {
        this.error = true;
        this.errorMessage = error;
      }
    );
  }
}
