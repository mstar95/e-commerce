import { Component, OnInit } from '@angular/core';
import {AuctionDetail} from '../auctionDetail';
import {Router} from '@angular/router';
import {AuctionService} from '../auction.service';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  auctionDetail: AuctionDetail = new AuctionDetail();
  loading = false;
  constructor(private router: Router, private auctionService: AuctionService) { }

  ngOnInit() {
  }

  add() {
    this.loading = true;
    this.auctionService.addAuction(this.auctionDetail)
      .subscribe((id) => this.goDetails(id));
  }

  goDetails(id: number) {
    if (id) {
      this.router.navigate(['auction-detail/' + id ]);
    }
    this.loading = false;
  }

}
