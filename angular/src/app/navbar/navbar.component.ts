import { Component, OnInit, OnDestroy } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';



@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})

export class NavbarComponent implements OnInit, OnDestroy {
  constructor(private readonly apiService: ApiService, private router: Router) { }

  isAdmin: boolean = false;
  isAuthenticated: boolean = false;
  searchValue: string = '';
  private authStatusSub: Subscription | null = null;



  ngOnInit(): void {
    this.isAuthenticated = this.apiService.isAuthenticated();
    this.isAdmin = this.apiService.isAdmin();

    this.authStatusSub = this.apiService.authStatuschanged.subscribe(() => {
      this.isAuthenticated = this.apiService.isAuthenticated();
      this.isAdmin = this.apiService.isAdmin();
    })
  }

  handleSearchSubmit() {
    this.router.navigate(['/home'], { queryParams: { search: this.searchValue } });
  }

  handleLogout() {
    const confirm = window.confirm("Are you sure you want to log out? ")
    if (confirm) {
      this.apiService.logout();
      this.router.navigate(['/login'])
      this.apiService.authStatuschanged.emit();
    }
  }

  ngOnDestroy(): void {
    if (this.authStatusSub) {
      this.authStatusSub.unsubscribe();
    }
  }

}
