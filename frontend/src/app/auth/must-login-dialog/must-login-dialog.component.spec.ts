import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MustLoginDialogComponent } from './must-login-dialog.component';

describe('MustLoginDialogComponent', () => {
  let component: MustLoginDialogComponent;
  let fixture: ComponentFixture<MustLoginDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MustLoginDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MustLoginDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
