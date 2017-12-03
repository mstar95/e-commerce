import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CreateUser} from '../createUser';
import {UserService} from '../user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: CreateUser = new CreateUser();
  loading = false;
  error = '';
  constructor(private router: Router,
              private userService: UserService) {
  }

  ngOnInit() {
  }

  register() {
    if (this.user.password !== this.user.rePassword) {
      this.error = 'Hasla nie rownaja sie';
      return;
    }
    this.error = '';
    this.loading = true;
    this.userService.createUser(this.user)
      .subscribe(result => {
          this.router.navigate(['my-account']);
      }, error => {
        this.loading = false;
        //  this.error = error;
      });
  }
}
