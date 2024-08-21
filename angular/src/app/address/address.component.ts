import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-address',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './address.component.html',
  styleUrl: './address.component.css'
})
export class AddressComponent implements OnInit {

  addressForm: FormGroup;
  error: any = null
  isEditMode: boolean;

  constructor(private apiService: ApiService, private fb: FormBuilder, private router: Router) {
    this.isEditMode = this.router.url.includes('edit-address')
    this.addressForm = this.fb.group({})
  }

  ngOnInit(): void {
    this.addressForm = this.fb.group({
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipCode: ['', Validators.pattern('^[0-9]*$')],
      country: ['', Validators.required]
    })



    if (this.isEditMode) {
        this.fetchUserAddressInfo();
    }
  }

  fetchUserAddressInfo():void{
    this.apiService.getLoggedInUserInfo().subscribe({
      next:(response)=>{
        if (response.user.address) {
          this.addressForm.patchValue(response.user.address)
        }
      },
      error:(error)=>{
        console.log(error)
        this.showError(error?.error?.message || "unable to get user address")
      }
    })
  }

  handleSubmit():void{
    if (this.addressForm.invalid) {
      this.showError("Please fill in all fields")
      return;
    }

    this.apiService.saveAddress(this.addressForm.value).subscribe({
      next:(response)=>{
        this.router.navigate(['/profile'])
      },
      error:(error)=>{
        console.log(error)
        this.showError(error?.error?.message || "unable to save user address")
      }
    })
  }

  showError(message: string){
    this.error = message
    setTimeout(()=>{
      this.error = null
    }, 3000)
  }

}
