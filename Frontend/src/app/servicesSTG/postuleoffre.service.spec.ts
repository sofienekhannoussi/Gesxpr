import { TestBed } from '@angular/core/testing';

import { PostuleoffreService } from './postuleoffre.service';

describe('PostuleoffreService', () => {
  let service: PostuleoffreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostuleoffreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
