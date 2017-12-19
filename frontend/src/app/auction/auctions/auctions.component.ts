import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuctionService} from '../auction.service';
import {Auction} from '../auction';
import 'rxjs/add/observable/of';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-auctions',
  templateUrl: './auctions.component.html',
  styleUrls: ['./auctions.component.css']
})
export class AuctionsComponent implements OnInit {

  constructor(private router: Router,
              private route: ActivatedRoute,
              private auctionService: AuctionService) {
  }

  auctions: Auction[];
  dataSource = new MatTableDataSource();
  displayedColumns = ['image', 'name', 'price', 'buyNow', 'date' ];

  getAuctions(params): void {
    const name = params.get('name');
    this.auctionService.getAuctionsByName(name)
      .subscribe(auctions => {
          this.auctions = auctions;
          this.dataSource.data = auctions.concat(auctions).concat(auctions).concat(auctions);
         // this.dataSource = new MyDataSource(auctions);
        },
        () => this.router.navigate(['login']));
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => this.getAuctions(params));
  }
}

