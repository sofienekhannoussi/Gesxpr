import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderResponsableComponent } from './header-responsable.component';

describe('HeaderResponsableComponent', () => {
  let component: HeaderResponsableComponent;
  let fixture: ComponentFixture<HeaderResponsableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderResponsableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderResponsableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
