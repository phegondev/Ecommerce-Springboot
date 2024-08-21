import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminorderdetailsComponent } from './adminorderdetails.component';

describe('AdminorderdetailsComponent', () => {
  let component: AdminorderdetailsComponent;
  let fixture: ComponentFixture<AdminorderdetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminorderdetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminorderdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
