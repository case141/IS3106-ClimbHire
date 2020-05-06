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
  professionalId: number;
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
    this.professionalId = parseInt(
      this.activatedRoute.snapshot.paramMap.get("professionalId")
    );

    this.professionalService
      .retrieveProfessionalById(this.professionalId)
      .subscribe(
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

  deleteProduct() {
    this.professionalService.deleteProduct(this.professionalId).subscribe(
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
