import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChargePointsDialogComponent } from './charge-points-dialog.component';

describe('ChargePointsDialogComponent', () => {
  let component: ChargePointsDialogComponent;
  let fixture: ComponentFixture<ChargePointsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChargePointsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChargePointsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
