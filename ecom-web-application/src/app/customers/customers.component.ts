import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers:any;
  constructor(private httpClient: HttpClient, private router:Router) { }

  ngOnInit(): void {
    this.httpClient.get("http://localhost:9999/CUSTOMER-SERVICE/customers").subscribe({
      next:(data)=>{
        this.customers = data;
      },
      error:(err)=>{}
    });
  }

  getBills(c:any) {
    this.router.navigateByUrl("/bills/"+c.id);
  }
}
