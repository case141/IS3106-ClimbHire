import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJobListingComponent } from './create-job-listing.component';

describe('CreateJobListingComponent', () => {
  let component: CreateJobListingComponent;
  let fixture: ComponentFixture<CreateJobListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateJobListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateJobListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
