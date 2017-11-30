import { Component, OnInit } from '@angular/core';
import {AuctionDetail} from '../auctionDetail';

@Component({
  selector: 'app-add-auction',
  templateUrl: './add-auction.component.html',
  styleUrls: ['./add-auction.component.css']
})
export class AddAuctionComponent implements OnInit {

  auctionDetail: AuctionDetail = new AuctionDetail();

  constructor() { }

  ngOnInit() {
  }

}
