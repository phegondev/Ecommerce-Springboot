import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from '../pagination/pagination.component';
import { ProductlistComponent } from '../productlist/productlist.component';
import { ApiService } from '../service/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-categoryproducts',
  standalone: true,
  imports: [CommonModule, PaginationComponent, ProductlistComponent],
  templateUrl: './categoryproducts.component.html',
  styleUrl: './categoryproducts.component.css'
})
export class CategoryproductsComponent implements OnInit {

  constructor(private apiService: ApiService, private route:ActivatedRoute){}
  products: any[] = [];
  currentPage = 1;
  totalPages = 0;
  itemsPerPage = 8;
  error: any = null;

  ngOnInit(): void {
      this.route.params.subscribe(params =>{
        const categoryId = params['categoryId']
        if (categoryId) {
          this.fetchProductsByCategory(categoryId);
        }
      })
  }

  fetchProductsByCategory(categoryId: string): void{
    this.apiService.getAllProductsByCategotyId(categoryId).subscribe({
      next: (response) =>{
        const allProducts = response.productList || []
        this.totalPages = Math.ceil(allProducts.length / this.itemsPerPage);
        this.products = allProducts.slice((this.currentPage -1) * this.itemsPerPage, this.currentPage * this.itemsPerPage); 
      },
      error:(error) =>{
        this.error = error?.error?.message || "Unable to Fetch all products by category id";
      }
    })
  }
  
  onPageChange(page: number):void{
    this.currentPage = page;
    const categoryId = this.route.snapshot.params['categoryId']
    this.fetchProductsByCategory(categoryId);
  }

}
