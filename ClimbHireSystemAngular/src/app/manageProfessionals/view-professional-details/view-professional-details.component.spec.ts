import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProfessionalDetailsComponent } from './view-professional-details.component';

describe('ViewProfessionalDetailsComponent', () => {
  let component: ViewProfessionalDetailsComponent;
  let fixture: ComponentFixture<ViewProfessionalDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewProfessionalDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProfessionalDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
