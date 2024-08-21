import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PaginationComponent } from '../pagination/pagination.component';
import { ProductlistComponent } from '../productlist/productlist.component';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, PaginationComponent, ProductlistComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  constructor(private apiService: ApiService, private router: Router, private route: ActivatedRoute) { }
  products: any[] = [];
  currentPage = 1;
  totalPages = 0;
  itemsPerPage = 8;
  error: any = null;

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params: ParamMap) => {
      const searchItem = params.get('search');
      const pageParam = params.get('page');
      this.currentPage = pageParam ? +pageParam : 1
      this.fetchProducts(searchItem)
    })
  }

  fetchProducts(searchItem: string | null): void {
    const productObservable = searchItem ? this.apiService.searchProducts(searchItem)
      : this.apiService.getAllProducts();

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

  changePage(page: number): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: { page },
      queryParamsHandling: 'merge'
    })
  }

}
