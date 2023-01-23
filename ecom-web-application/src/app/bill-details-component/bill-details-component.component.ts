import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bill-details-component',
  templateUrl: './bill-details-component.component.html',
  styleUrls: ['./bill-details-component.component.css']
})
export class BillDetailsComponentComponent implements OnInit {

  billDetails:any;
  billId!:number;
  constructor(private http: HttpClient, private route:ActivatedRoute, private router:Router) {
      //this.billId = 1;
      this.billId = route.snapshot.params['bId'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:9999/BILLING-SERVICE/fullBill/"+this.billId).subscribe({
      next:(data)=>{
        this.billDetails = data;
      },
      error:(err)=>{}
    });
  }

}
