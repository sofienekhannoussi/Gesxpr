import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionMessionsComponent } from './gestion-messions.component';

describe('GestionMessionsComponent', () => {
  let component: GestionMessionsComponent;
  let fixture: ComponentFixture<GestionMessionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionMessionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionMessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
