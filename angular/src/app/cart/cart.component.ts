import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CartService } from '../service/cart.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  constructor(private apiService:ApiService, private cartService:CartService, private router:Router) {}

  cart:any[] = [];
  message: any = null;
  totalPrice:number = 0;

  ngOnInit(): void {
      this.loadCart();
  }

  loadCart():void{
    this.cart = this.cartService.getCart();
    this.calculateTotalPrice()
  }

  calculateTotalPrice():void{
    this.totalPrice = this.cart.reduce((total, item) => total + item.price * item.quantity, 0)
  }

  incrementItem(itemId: number):void{
    this.cartService.incrementItem(itemId)
    this.loadCart();
  }

  decrementItem(itemId: number):void{
    this.cartService.decrementItem(itemId)
    this.loadCart();
  }

  removeItem(itemId: number):void{
    this.cartService.removeItem(itemId)
    this.loadCart();
  }
  clearCart():void{
    this.cartService.clearCart()
    this.loadCart();
  }


  handleCheckOut():void{
    if (!this.apiService.isAuthenticated()) {
      this.message = "you need to login before you can place an order"
      setTimeout(()=>{
        this.message = null
        this.router.navigate(['/login'])
      }, 3000)
      return;
    }

    const orderItems = this.cart.map(item =>({
      productId: item.id,
      quantity: item.quantity
    }));

    const orderRequest = {
      totalPrice: this.totalPrice,
      items: orderItems
    }

    this.apiService.createOrder(orderRequest).subscribe({
      next:(response)=>{
        this.message = (response.message)
        if (response.status === 200) {
          this.cartService.clearCart();
          this.loadCart();
        }
      },
      error:(error)=>{
        console.log(error)
        this.message = error?.error?.message || "unable to place order"
      }
    })




  }




}
