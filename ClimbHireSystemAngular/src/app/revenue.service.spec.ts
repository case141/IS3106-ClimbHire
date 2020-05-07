import { TestBed } from '@angular/core/testing';

import { RevenueService } from './revenue.service';

describe('RevenueService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RevenueService = TestBed.get(RevenueService);
    expect(service).toBeTruthy();
  });
});
