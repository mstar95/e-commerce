import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {AuctionService} from '../auction.service';
import {AuctionDetail} from '../auctionDetail';
import {AuthenticationService} from '../../auth/authentication.service';
import {MatDialog} from '@angular/material';
import {MustLoginDialogComponent} from '../../auth/must-login-dialog/must-login-dialog.component';

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.css']
})
export class SaleDetailComponent implements OnInit {
  @Input() auction: AuctionDetail;
  constructor(private route: ActivatedRoute,
              private auctionService: AuctionService,
              private location: Location,
              private authenticationService: AuthenticationService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getAuction();
  }

  getAuction(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.auctionService.getAuction(id)
      .subscribe(auction => this.auction = auction);
  }

  buy(): void {
    if (this.authenticationService.isLoggedIn()) {
      this.auctionService.buy(this.auction.id).subscribe();
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
