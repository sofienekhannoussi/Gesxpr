import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationPopupRespComponent } from './education-popup-resp.component';

describe('EducationPopupRespComponent', () => {
  let component: EducationPopupRespComponent;
  let fixture: ComponentFixture<EducationPopupRespComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducationPopupRespComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EducationPopupRespComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
