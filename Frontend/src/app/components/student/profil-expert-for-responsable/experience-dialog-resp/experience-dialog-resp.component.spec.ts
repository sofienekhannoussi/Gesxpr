import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExperienceDialogRespComponent } from './experience-dialog-resp.component';

describe('ExperienceDialogRespComponent', () => {
  let component: ExperienceDialogRespComponent;
  let fixture: ComponentFixture<ExperienceDialogRespComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExperienceDialogRespComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExperienceDialogRespComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
