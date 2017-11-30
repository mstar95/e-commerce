import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {Router} from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private location: Location,
              private router: Router) {
  }

  ngOnInit() {
    this.getUser();
  }

  getUser(): void {
    this.userService.getCurrentUser()
      .subscribe(user => this.user = user);
  }

  goBack(): void {
    this.location.back();
  }

  addAuction(): void {
    this.router.navigate(['add-auction']);
  }

}
