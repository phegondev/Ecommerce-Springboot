import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from '../../pagination/pagination.component';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminproduct',
  standalone: true,
  imports: [PaginationComponent, CommonModule],
  templateUrl: './adminproduct.component.html',
  styleUrl: './adminproduct.component.css'
})
export class AdminproductComponent implements OnInit {

  constructor(private apiService: ApiService, private router: Router) { }
  products: any[] = [];
  currentPage: number = 1;
  totalPages: number = 0;
  itemsPerPage: number = 10;
  error: string = ''



  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    const productObservable = this.apiService.getAllProducts();

    productObservable.subscribe({
      next: (response) => {
        if (response?.productList && response.productList.length > 0) {
          this.handleProductResponse(response.productList)
        } else {
          this.error = 'Product Not Found'
        }
      },
      error: (error) => {
        console.log(error)
        this.error = error?.error?.message || "error getting products";
      }
    })

  }

  handleProductResponse(products: []): void {
    this.totalPages = Math.ceil(products.length / this.itemsPerPage);
    this.products = products.slice(
      (this.currentPage - 1) * this.itemsPerPage,
      this.currentPage * this.itemsPerPage
    )
    console.log(this.products)
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.fetchProducts();
  }

  handleEdit(productId: string): void {
    this.router.navigate([`/admin/edit-product/${productId}`])
  }

  goToAddProduct(): void {
    this.router.navigate([`/admin/add-product`])
  }


  handleDelete(id: string): void {
    const confirm = window.confirm("Are you sure you wanna delete this product?")
    if (confirm) {
      this.apiService.deletProduct(id).subscribe({
        next: () => {
          this.fetchProducts();
        },
        error: (error) => {
          console.log(error)
        }
      })
    }
  }

}
