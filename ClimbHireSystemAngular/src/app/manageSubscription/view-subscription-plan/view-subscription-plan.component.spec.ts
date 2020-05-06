import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSubscriptionPlanComponent } from './view-subscription-plan.component';

describe('ViewSubscriptionPlanComponent', () => {
  let component: ViewSubscriptionPlanComponent;
  let fixture: ComponentFixture<ViewSubscriptionPlanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewSubscriptionPlanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSubscriptionPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
