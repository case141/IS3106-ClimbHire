import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllJobListingsComponent } from './view-all-job-listings.component';

describe('ViewAllJobListingsComponent', () => {
  let component: ViewAllJobListingsComponent;
  let fixture: ComponentFixture<ViewAllJobListingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllJobListingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllJobListingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
