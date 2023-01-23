import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {

  bills:any;
  customerId!:number;
  constructor(private httpClient: HttpClient, private route:ActivatedRoute, private router:Router) {
    this.customerId = route.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    this.httpClient.get("http://localhost:9999/BILLING-SERVICE/bills/search/byCustomerId?customerId="+this.customerId).subscribe({
      next:(data)=>{
        this.bills = data;
      },
      error:(err)=>{}
    });
  }

  getBillDetails(b: any){
    this.router.navigateByUrl("/bill-details/"+b.id)
  }
}
