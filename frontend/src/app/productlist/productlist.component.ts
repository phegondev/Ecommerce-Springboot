import { Component, Input } from '@angular/core';
import { CartService } from '../service/cart.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


interface Product{
  id: string,
  name: string,
  description: string,
  price: number,
  imageUrl: string
}



@Component({
  selector: 'app-productlist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './productlist.component.html',
  styleUrl: './productlist.component.css'
})
export class ProductlistComponent {

  constructor(private cartService:CartService, private router:Router){}

  @Input() products: Product[] = []

  addToCart(product: Product): void{
    this.cartService.addItem(product)
  }

  incrementItem(product: Product): void{
    this.cartService.incrementItem(Number(product.id))
  }
  
  decrementItem(product: Product): void{
    this.cartService.decrementItem(Number(product.id))
  }
  
  inInCart(product: Product): boolean{
    return this.cartService.getCart().some(item => item.id === product.id)
  }

  getCartItem(product: Product): any{
    return this.cartService.getCart().find(item => item.id === product.id)
  }

  goToProductDetailsPage(productId: string): void{
    this.router.navigate(['/product', productId]);
  }

}
