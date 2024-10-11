import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SKillsDialogRespComponent } from './skills-dialog-resp.component';

describe('SKillsDialogRespComponent', () => {
  let component: SKillsDialogRespComponent;
  let fixture: ComponentFixture<SKillsDialogRespComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SKillsDialogRespComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SKillsDialogRespComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
