import { Component } from '@angular/core';
import {SecurityService} from "./services/security.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ecom-web-application';

  constructor(public securityService:SecurityService) {
  }

  async login() {
      await this.securityService.kcService.login({
        redirectUri: window.location.origin
      })
  }

  onLogout() {
    this.securityService.kcService.logout(window.location.origin);
  }
}
