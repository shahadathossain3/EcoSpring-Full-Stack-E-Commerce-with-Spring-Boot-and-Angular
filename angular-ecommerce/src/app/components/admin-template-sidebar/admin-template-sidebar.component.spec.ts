import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTemplateSidebarComponent } from './admin-template-sidebar.component';

describe('AdminTemplateSidebarComponent', () => {
  let component: AdminTemplateSidebarComponent;
  let fixture: ComponentFixture<AdminTemplateSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminTemplateSidebarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTemplateSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
