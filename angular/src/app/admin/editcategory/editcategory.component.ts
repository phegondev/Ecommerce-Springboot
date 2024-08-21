import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-editcategory',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './editcategory.component.html',
  styleUrl: './editcategory.component.css'
})

export class EditcategoryComponent implements OnInit {
  categoryForm: FormGroup;
  message: any = null;
  categoryId: string = ''

  constructor(private apiService: ApiService, private router: Router, private fb: FormBuilder, private route: ActivatedRoute) {

    this.categoryForm = this.fb.group({
      name: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get("categoryId") || '';
    this.fetchCategoryById();
  }

  fetchCategoryById(): void {

    if (this.categoryId) {
      this.apiService.getCategoryById(this.categoryId).subscribe({
        next: (response) => {
          this.categoryForm.patchValue({ name: response.category.name })
        },
        error: (error) => {
          console.log((error))
          this.message = error?.error?.message || "unable to get categoty by id";
          setTimeout(() => {
            this.message = null;
          }, 3000)
        }
      })
    }
  }



  handleSubmit(): void {
    if (this.categoryForm.valid) {
      this.apiService.updateCategory(this.categoryId, this.categoryForm.value).subscribe({
        next: (response) => {
          if (response.status === 200) {
            this.message = "Category successfully Updated"
            setTimeout(() => {
              this.message = null;
              this.router.navigate(['/admin/categories']);
            }, 3000)
          }
        },
        error: (error) => {
          console.log(error)
          this.message = error?.error?.message || "unable to update Category";
        }
      })
    }
  }

}
