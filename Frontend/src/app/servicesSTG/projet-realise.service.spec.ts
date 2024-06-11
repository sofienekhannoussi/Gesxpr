import { TestBed } from '@angular/core/testing';

import { ProjetRealiseService } from './projet-realise.service';

describe('ProjetRealiseService', () => {
  let service: ProjetRealiseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjetRealiseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
