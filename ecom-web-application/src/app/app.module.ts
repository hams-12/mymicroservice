import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './products/products.component';
import {HttpClientModule} from "@angular/common/http";
import { CustomersComponent } from './customers/customers.component';
import { BillsComponent } from './bills/bills.component';
import { BillDetailsComponentComponent } from './bill-details-component/bill-details-component.component';
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";

export function kcFactory(kcService:KeycloakService){
  return ()=>{
    kcService.init({
      config:{
        realm:"Ecom-realm",
        clientId:"ecom-client",
        url:"http://localhost:7070"
      },
      initOptions:{
        onLoad:"check-sso",
        checkLoginIframe:true
      }
    })
  }
}
@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CustomersComponent,
    BillsComponent,
    BillDetailsComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    {provide:APP_INITIALIZER, deps:[KeycloakService],useFactory:kcFactory, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
