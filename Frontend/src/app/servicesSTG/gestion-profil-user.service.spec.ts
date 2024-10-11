import { TestBed } from '@angular/core/testing';

import { GestionProfilUserService } from './gestion-profil-user.service';

describe('GestionProfilUserService', () => {
  let service: GestionProfilUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionProfilUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
