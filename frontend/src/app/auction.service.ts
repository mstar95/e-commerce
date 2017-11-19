///<reference path="model/mock-auctions.ts"/>
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { MessageService } from './message.service';
import {Auction} from './model/auction';
import { AUCTIONS } from './model/mock-auctions';

@Injectable()
export class AuctionService {

  getAuction(id: number): Observable<Auction> {
    // Todo: send the message _after_ fetching the hero
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return of(AUCTIONS.find(hero => hero.id === id));
  }

  getAuctions(): Observable<Auction[]> {
    this.messageService.add('AuctionService: fetched auctions');
    return of(AUCTIONS);
  }

  constructor(private messageService: MessageService) { }

}
