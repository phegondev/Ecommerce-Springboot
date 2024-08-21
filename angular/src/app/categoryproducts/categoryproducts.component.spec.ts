import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryproductsComponent } from './categoryproducts.component';

describe('CategoryproductsComponent', () => {
  let component: CategoryproductsComponent;
  let fixture: ComponentFixture<CategoryproductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryproductsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryproductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
