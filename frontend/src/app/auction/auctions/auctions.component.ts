import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuctionService } from '../auction.service';
import {Auction} from '../auction';

@Component({
  selector: 'app-auctions',
  templateUrl: './auctions.component.html',
  styleUrls: ['./auctions.component.css']
})
export class AuctionsComponent implements OnInit {

  constructor(private router: Router, private auctionService: AuctionService) { }

  auctions: Auction[];

  getAuctions(): void {
    this.auctionService.getAuctions()
      .subscribe(auctions => this.auctions = auctions,
        () => this.router.navigate(['login']));
  }

  ngOnInit() {
    this.getAuctions();
  }
}
