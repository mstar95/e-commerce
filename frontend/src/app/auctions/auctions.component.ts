import { Component, OnInit } from '@angular/core';
import { AuctionService } from '../auction.service';
import {Auction} from '../model/auction';

@Component({
  selector: 'app-auctions',
  templateUrl: './auctions.component.html',
  styleUrls: ['./auctions.component.css']
})
export class AuctionsComponent implements OnInit {

  constructor(private auctionService: AuctionService) { }

  auctions: Auction[];

  getAuctions(): void {
    this.auctionService.getAuctions()
      .subscribe(auctions => this.auctions = auctions);
  }

  ngOnInit() {
    this.getAuctions();
  }
}
