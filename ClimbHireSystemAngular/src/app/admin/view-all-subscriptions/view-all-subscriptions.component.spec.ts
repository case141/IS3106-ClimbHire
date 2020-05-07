import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllSubscriptionsComponent } from './view-all-subscriptions.component';

describe('ViewAllSubscriptionsComponent', () => {
  let component: ViewAllSubscriptionsComponent;
  let fixture: ComponentFixture<ViewAllSubscriptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllSubscriptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
