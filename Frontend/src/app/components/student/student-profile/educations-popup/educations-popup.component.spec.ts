import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationsPopupComponent } from './educations-popup.component';

describe('EducationsPopupComponent', () => {
  let component: EducationsPopupComponent;
  let fixture: ComponentFixture<EducationsPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducationsPopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EducationsPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
