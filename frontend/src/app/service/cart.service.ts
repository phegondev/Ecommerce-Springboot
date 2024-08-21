import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cart: any[] = [];

  constructor() {
    const savedCart = localStorage.getItem('cart');
    this.cart = savedCart ? JSON.parse(savedCart) : [];
  }

  getCart() {
    return this.cart;
  }

  getCartItem(itemId: number) {
    return this.cart.find(cartItem => cartItem.id === itemId);
  }

  private saveCart() {
    localStorage.setItem('cart', JSON.stringify(this.cart));
  }

  addItem(item: any) {
    const existingItem = this.getCartItem(item.id);
    if (existingItem) {
      existingItem.quantity += 1;
    } else {
      this.cart.push({ ...item, quantity: 1 })
    }
    this.saveCart();
  }

  removeItem(itemId: number) {
    this.cart = this.cart.filter(item => item.id !== itemId);
    this.saveCart();
  }

  incrementItem(itemId: number) {
    const item = this.getCartItem(itemId);
    if (item) {
      item.quantity += 1;
      this.saveCart();
    }
  }

  decrementItem(itemId: number) {
    const item = this.getCartItem(itemId);
    if (item && item.quantity > 1) {
      item.quantity -= 1;
      this.saveCart();
    } else {
      this.removeItem(itemId);
    }
  }

  clearCart() {
    this.cart = [];
    localStorage.removeItem('cart')
  }


}
