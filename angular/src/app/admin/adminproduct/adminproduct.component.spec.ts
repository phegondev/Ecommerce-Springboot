import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminproductComponent } from './adminproduct.component';

describe('AdminproductComponent', () => {
  let component: AdminproductComponent;
  let fixture: ComponentFixture<AdminproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminproductComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
