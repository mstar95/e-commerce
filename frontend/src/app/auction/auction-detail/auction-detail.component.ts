import { Component, OnInit, Input  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import {Auction} from '../auction';
import {AuctionService} from '../auction.service';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  @Input() auction: Auction;

  constructor(
    private route: ActivatedRoute,
    private auctionService: AuctionService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getAuction();
  }

  getAuction(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.auctionService.getAuction(id)
      .subscribe(auction => this.auction = auction);
  }

  save(): void {
    this.auctionService.saveAuction(this.auction)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
