import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProductOrderComponent } from './list-product-order.component';

describe('ListProductOrderComponent', () => {
  let component: ListProductOrderComponent;
  let fixture: ComponentFixture<ListProductOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListProductOrderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListProductOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
