import { Component, OnInit } from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {

  user: User;

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.getUser();
  }

  getUser(): void {
    this.userService.getCurrentUser()
      .subscribe(user => this.user = user);
  }

  goMyAccount(): void {
    this.router.navigate(['my-account']);
  }

  goLogin(): void {
    this.router.navigate(['login']);
  }

  goRegister(): void {
    this.router.navigate(['register']);
  }
}
