import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersOrderDetailsComponent } from './users-order-details.component';

describe('UsersOrderDetailsComponent', () => {
  let component: UsersOrderDetailsComponent;
  let fixture: ComponentFixture<UsersOrderDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersOrderDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersOrderDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
