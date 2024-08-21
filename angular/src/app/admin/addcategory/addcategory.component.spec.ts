import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcategoryComponent } from './addcategory.component';

describe('AddcategoryComponent', () => {
  let component: AddcategoryComponent;
  let fixture: ComponentFixture<AddcategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddcategoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddcategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
