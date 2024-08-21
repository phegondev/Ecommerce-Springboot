import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../service/api.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-adminorderdetails',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './adminorderdetails.component.html',
  styleUrl: './adminorderdetails.component.css'
})
export class AdminorderdetailsComponent implements OnInit {
  orderItem: any[] = []
  selectedStatus: {[key:number]: string} = {}
  orderId:any = ''
  message: any = null
  OderStatus = ["PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED", "RETURNED"]

  constructor(private apiService: ApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.paramMap.get('orderId')
    this.fetchOrderDetails()
  }

  fetchOrderDetails():void{
    if (this.orderId) {
        this.apiService.getOrderItemById(this.orderId).subscribe({
          next:(res)=>{
            this.orderItem = res.orderItemList || [];
            this.orderItem.forEach(item =>{
              this.selectedStatus[item.id] = item.status
            })
          },
          error:(error) =>{
            console.log(error)
          }
        })
    }
  }

  handleStatusChange(orderId: number, newStatus: string):void{
    this.selectedStatus[orderId] = newStatus
  }

  
  handleSubmitStatusChange(orderId: number):void{
    this.apiService.updateOrderItemStatus(orderId.toString(), this.selectedStatus[orderId]).subscribe({
      next:(res)=>{
        this.fetchOrderDetails();
        this.message = "Order item status was successfuklly updated";
        setTimeout(()=>{
         this .message = null;
        }, 4000)
      }
    })
  }
}
