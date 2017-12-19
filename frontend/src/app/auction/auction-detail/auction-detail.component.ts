import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {AuctionService} from '../auction.service';
import {AuctionDetail} from '../auctionDetail';
import {AuthenticationService} from '../../auth/authentication.service';
import {MatDialog} from '@angular/material';
import {MustLoginDialogComponent} from '../../auth/must-login-dialog/must-login-dialog.component';
import {PaymentService} from "../payment.service";

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  @Input() auction: AuctionDetail;
  bidAmount: number;
  token: any;
  error: string;
  constructor(private route: ActivatedRoute,
              private auctionService: AuctionService,
              private paymentService: PaymentService,
              private location: Location,
              private authenticationService: AuthenticationService,
              public dialog: MatDialog) {
    this.token = this.authenticationService.getToken();
  }

  ngOnInit(): void {
    this.getAuction();
  }

  getAuction(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.auctionService.getAuction(id)
      .subscribe(auction => this.auction = auction);
  }

  bid(): void {
    if (this.authenticationService.isLoggedIn()) {
      if (!this.bidAmount || this.bidAmount <= this.auction.price) {
        this.error = 'Zla kwota';
        return;
      }
      this.paymentService.bid(this.auction.id, this.bidAmount).subscribe(() => this.getAuction());
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
