import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmincategoryComponent } from './admincategory.component';

describe('AdmincategoryComponent', () => {
  let component: AdmincategoryComponent;
  let fixture: ComponentFixture<AdmincategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdmincategoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmincategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
