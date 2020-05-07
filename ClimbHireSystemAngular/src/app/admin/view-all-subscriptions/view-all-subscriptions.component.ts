import { Component, OnInit } from "@angular/core";
import { Subscription } from "../../subscription";
import { SubscriptionService } from "src/app/subscription.service";
import { SubscriptionStatusEnum } from "../../subscription-status-enum.enum";
import { SubscriptionTypeEnum } from "../../subscription-type-enum.enum";

@Component({
  selector: "app-view-all-subscriptions",
  templateUrl: "./view-all-subscriptions.component.html",
  styleUrls: ["./view-all-subscriptions.component.css"],
})
export class ViewAllSubscriptionsComponent implements OnInit {
  subscriptions: Subscription[];
  errorMessage: string;

  constructor(private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.subscriptionService.getSubscriptions().subscribe(
      (response) => {
        this.subscriptions = response.subscriptions;
      },
      (error) => {
        this.errorMessage = error;
      }
    );
  }
}
