import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewJobListingDetailsComponent } from './view-job-listing-details.component';

describe('ViewJobListingDetailsComponent', () => {
  let component: ViewJobListingDetailsComponent;
  let fixture: ComponentFixture<ViewJobListingDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewJobListingDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewJobListingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
