import {Component, OnInit} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';

import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';

import {Auction} from '../auction';
import {AuctionService} from '../auction.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auction-search',
  templateUrl: './auction-search.component.html',
  styleUrls: [ './auction-search.component.css' ]
})
export class AuctionSearchComponent implements OnInit {
  auctions$: Observable<Auction[]>;
  private searchTerms = new Subject<string>();

  constructor(private auctionService: AuctionService,
              private router: Router) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.auctions$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.auctionService.searchAuction(term)),
    );
  }

  goActions(name: string) {
    this.router.navigate(['/auctions/' + name]);
  }
}
