import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './auth/authentication.service';
import {MessageService} from './message/message.service';
import {Message} from './message/message';
import {UserService} from "./user/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  navLinks: any;
  notNeedToLogNavLinks = [
    {label: 'Tablica', path: '/dashboard'},
    {label: 'Aukcje', path: '/auctions'}
  ];

  needToLogNavLinks = [ {label: 'Dodaj Oferte', path: '/add-auction'}];
  title = 'Sklep';

  messages: Message[];
  notify = false;

  constructor(private authenticationService: AuthenticationService,
              private messageService: MessageService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.getMessages();
    this.navLinks = this.notNeedToLogNavLinks;
    if (this.isLoggedIn()) {
      this.navLinks = this.notNeedToLogNavLinks.concat(this.needToLogNavLinks);
    }
  }

  logout() {
    this.authenticationService.logout();
    this.userService.refreshToken();
    this.userService.triggerGetUser();
  }

  markAsSeen() {
    this.notify = false;
  }

  isLoggedIn() {
    return this.authenticationService.isLoggedIn();
  }

  private getMessages() {
    if (this.isLoggedIn()) {
      this.messageService.getAll()
        .subscribe(messages => {
          this.messages = messages;
          this.notify = messages.find(message => !message.seen) != null;
        });
    }
  }
}
