import { Component, OnInit } from '@angular/core';
import { Auction } from '../auction/auction';
import { AuctionService } from '../auction/auction.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  auctions: Auction[] = [];

  constructor(private auctionService: AuctionService) { }

  ngOnInit() {
    this.getAuctions();
  }

  getAuctions(): void {
    this.auctionService.getAuctions()
      .subscribe(auctions => this.auctions = auctions.slice(0, 20).concat(auctions.slice(0, 20)).concat(auctions.slice(0, 20)) );
  }
}
