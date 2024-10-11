import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducationDialogRespComponent } from './education-dialog-resp.component';

describe('EducationDialogRespComponent', () => {
  let component: EducationDialogRespComponent;
  let fixture: ComponentFixture<EducationDialogRespComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducationDialogRespComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EducationDialogRespComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
