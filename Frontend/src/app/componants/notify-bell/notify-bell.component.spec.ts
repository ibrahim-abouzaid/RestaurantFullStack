import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotifyBellComponent } from './notify-bell.component';

describe('NotifyBellComponent', () => {
  let component: NotifyBellComponent;
  let fixture: ComponentFixture<NotifyBellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotifyBellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotifyBellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
