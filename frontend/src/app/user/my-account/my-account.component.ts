import {Component, OnInit} from '@angular/core';
import {User} from '../user';
import {UserService} from '../user.service';
import {Router} from '@angular/router';
import { Location } from '@angular/common';
import {MatDialog} from "@angular/material";
import {MustLoginDialogComponent} from "../../auth/must-login-dialog/must-login-dialog.component";
import {ChargePointsDialogComponent} from "../charge-points-dialog/charge-points-dialog.component";
import {PaymentService} from "../../auction/payment.service";

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private paymentService: PaymentService,
              private location: Location,
              private router: Router,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.getUser();
  }

  getUser(): void {
    this.userService.getCurrentUser()
      .subscribe(user => this.user = user);
    this.userService.triggerGetUser();
  }

  goBack(): void {
    this.location.back();
  }

  addAuction(): void {
    this.router.navigate(['add-auction']);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ChargePointsDialogComponent, {
      width: '300px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.paymentService.chargePoints(result)
        .subscribe(() => this.userService.triggerGetUser());
    })
  }

}
