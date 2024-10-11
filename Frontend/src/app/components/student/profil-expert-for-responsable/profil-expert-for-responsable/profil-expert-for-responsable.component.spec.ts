import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilExpertForResponsableComponent } from './profil-expert-for-responsable.component';

describe('ProfilExpertForResponsableComponent', () => {
  let component: ProfilExpertForResponsableComponent;
  let fixture: ComponentFixture<ProfilExpertForResponsableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilExpertForResponsableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilExpertForResponsableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
