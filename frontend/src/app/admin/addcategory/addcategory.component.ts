import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addcategory',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './addcategory.component.html',
  styleUrl: './addcategory.component.css'
})
export class AddcategoryComponent {
  categoryForm: FormGroup;
  message: any = null;

  constructor(private apiService:ApiService, private router:Router, private fb:FormBuilder){

    this.categoryForm = this.fb.group({
      name: ['', Validators.required]
    })
  }

  handleSubmit():void{
    if(this.categoryForm.valid){
      this.apiService.createCategory(this.categoryForm.value).subscribe({
        next:(response) =>{
          if (response.status === 200) {
            this.message = "categoty successfully saved"
            setTimeout(()=>{
              this.message = null;
              this.router.navigate(['/admin/categories']);
            }, 3000)
          }
        },
        error:(error) => {
          console.log(error)
          this.message = error?.error?.message || "unable to save category";
        }
      })
    }
  }

}
