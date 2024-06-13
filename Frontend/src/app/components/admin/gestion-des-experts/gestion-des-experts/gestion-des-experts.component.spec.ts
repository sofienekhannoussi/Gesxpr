import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDesExpertsComponent } from './gestion-des-experts.component';

describe('GestionDesExpertsComponent', () => {
  let component: GestionDesExpertsComponent;
  let fixture: ComponentFixture<GestionDesExpertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDesExpertsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionDesExpertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
