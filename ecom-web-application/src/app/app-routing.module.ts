import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductsComponent} from "./products/products.component";
import {CustomersComponent} from "./customers/customers.component";
import {BillsComponent} from "./bills/bills.component";
import {BillDetailsComponentComponent} from "./bill-details-component/bill-details-component.component";
import {AuthGuard} from "./guards/security.guard";

const routes: Routes = [
  {
    path: "products", component: ProductsComponent,
    canActivate: [AuthGuard], data: {roles:['USER','ADMIN']}
  },
  {
    path: "customers", component: CustomersComponent,
    canActivate: [AuthGuard], data: {roles:['USER']}
  },
  {
    path: "bills/:customerId", component: BillsComponent
  },
  {
    path: "bill-details/:bId", component: BillDetailsComponentComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
