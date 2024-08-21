import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admincategory',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admincategory.component.html',
  styleUrl: './admincategory.component.css'
})
export class AdmincategoryComponent implements OnInit {

  categories: any[] = [];
  constructor(private apiService: ApiService, private router: Router) { }


  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.apiService.getAllCategory().subscribe({
      next: (response) => {
        this.categories = response.categoryList || []
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  handleEdit(id: string): void {
    this.router.navigate([`/admin/edit-category/${id}`])
  }

  handleDelete(id: string): void {
    const confirm = window.confirm("Are you sure you wanna delete this category?")
    if (confirm) {
      this.apiService.deleteCategory(id).subscribe({
        next: () => {
          this.fetchCategories();
        },
        error: (error) => {
          console.log(error)
        }
      })
    }
  }

  addCategoty(): void {
    this.router.navigate([`/admin/add-category`])
  }

}

