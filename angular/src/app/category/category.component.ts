import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  constructor(private apiService:ApiService, private router:Router){}

  error:any = null;
  categories: any[] = [];

  ngOnInit(): void {
      this.fetchCategories();
  }
  
  fetchCategories():void{
    this.apiService.getAllCategory().subscribe({
      next: (response) =>{
        this.categories = response.categoryList || []
      },
      error: (err) =>{
        this.error = err?.error?.message || 'unable to get categories'
      }
    })
  }

  handleCategoryClick(categoryId: number): void{
    this.router.navigate(['/products', categoryId])
  }

}
