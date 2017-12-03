import {Component} from '@angular/core';
import {AuthenticationService} from "./auth/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  navLinks = [
    {label: 'Tablica', path: '/dashboard'},
    {label: 'Aukcje', path: '/auctions'},
    {label: 'Dodaj Oferte', path: '/add-auction'},
  ];
  title = 'Sklep';

  constructor(private authenticationService: AuthenticationService) {
  }

  logout() {
    this.authenticationService.logout();
  }

}
