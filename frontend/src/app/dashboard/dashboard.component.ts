import { Component, OnInit } from '@angular/core';
import { Auction } from '../auction/auction';
import { AuctionService } from '../auction/auction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  auctions: Auction[] = [];

  constructor(private auctionService: AuctionService,
              private router: Router ) { }

  ngOnInit() {
    this.getAuctions();
  }

  getAuctions(): void {
    this.auctionService.getAuctions()
      .subscribe(auctions => this.auctions = auctions.slice(0, 20));
  }

  goDetails(id: number): void {
    if (this.auctions.find(auction => auction.id === id).buyNow) {
      this.router.navigate(['/sale-detail/' + id]);
    } else {
      this.router.navigate(['/auction-detail/' + id]);
    }
  }

}
