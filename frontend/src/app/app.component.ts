import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './auth/authentication.service';
import {MessageService} from './message/message.service';
import {Message} from './message/message';
import {UserService} from './user/user.service';
import {Router} from "@angular/router";

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
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authenticationService.getLoginObservalbe().subscribe(state => {
      if (state) {
        this.navLinks = this.notNeedToLogNavLinks.concat(this.needToLogNavLinks);
        this.getMessages();
      } else {
        this.navLinks = this.notNeedToLogNavLinks;
      }
    });
  }

  logout() {
    this.router.navigate(['dashboard']);
    this.authenticationService.logout();
    this.userService.triggerGetUser();
  }

  markAsSeen() {
    if (this.notify) {
      this.notify = false;
      this.messageService.markSeen().subscribe();
    }
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
