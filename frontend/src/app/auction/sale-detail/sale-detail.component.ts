import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {AuctionService} from '../auction.service';
import {AuctionDetail} from '../auctionDetail';
import {AuthenticationService} from '../../auth/authentication.service';
import {MatDialog} from '@angular/material';
import {MustLoginDialogComponent} from '../../auth/must-login-dialog/must-login-dialog.component';
import {UserService} from "../../user/user.service";
import {User} from "../../user/user";
import {PaymentService} from "../payment.service";

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.css']
})
export class SaleDetailComponent implements OnInit {
  @Input() auction: AuctionDetail;
  private user: User;
  constructor(private route: ActivatedRoute,
              private auctionService: AuctionService,
              private paymentService: PaymentService,
              private location: Location,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getAuction();
  }

  getAuction(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getCurrentUser().subscribe();
    this.auctionService.getAuction(id)
      .subscribe(auction => { this.auction = auction; console.log(this.auction); });
  }

  buy(): void {
    if (this.authenticationService.isLoggedIn()) {
      this.paymentService.buy(this.auction.id).subscribe();
    } else {
      this.openDialog();
    }
  }

  openDialog() {
    const dialogRef = this.dialog.open(MustLoginDialogComponent, {
      width: '300px'
    });
  }

  goBack(): void {
    this.location.back();
  }
}
