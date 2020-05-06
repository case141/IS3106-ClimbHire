import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllProfessionalsComponent } from './view-all-professionals.component';

describe('ViewAllProfessionalsComponent', () => {
  let component: ViewAllProfessionalsComponent;
  let fixture: ComponentFixture<ViewAllProfessionalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllProfessionalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllProfessionalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
