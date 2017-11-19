///<reference path="model/mock-auctions.ts"/>
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import {Auction} from './model/auction';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable()
export class AuctionService {

  private auctionsUrl = 'api/search';  // URL to web api

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getAuction(id: number): Observable<Auction> {
    const url = `${this.auctionsUrl}/${id}`;
    return this.http.get<Auction>(url).pipe(
      tap(_ => this.log(`fetched hero id=${id}`)),
      catchError(this.handleError<Auction>(`getAuction id=${id}`))
    );
  }

  getAuctions(): Observable<Auction[]> {
    this.log('AuctionService: fetched auctions');
    return this.http.get<Auction[]>(this.auctionsUrl + '/all')
      .pipe(
        catchError(this.handleError('getAuctions', []))
      );
  }

  saveAuction (auction: Auction): Observable<any> {
    return this.http.put(this.auctionsUrl, auction, httpOptions).pipe(
      tap(_ => this.log(`updated auction id=${auction.id}`)),
      catchError(this.handleError<any>('saveAuction'))
    );
  }

  /* GET heroes whose name contains search term */
  searchAuction(term: string): Observable<Auction[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Auction[]>(`api/search/?name=${term}`).pipe(
      tap(_ => this.log(`found auction matching "${term}"`)),
      catchError(this.handleError<Auction[]>('searchAuctions', []))
    );
  }

  private log(message: string) {
    this.messageService.add('HeroService: ' + message);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
