import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Auction} from './auction';
import {AuctionDetail} from './auctionDetail';
import {AuthenticationService} from '../auth/authentication.service';

@Injectable()
export class AuctionService {

  private auctionsUrl = 'api/sale';  // URL to web api

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '
    })
  };

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
    this.authenticationService.getTokenObs()
      .subscribe(token => this.setHttpOptions(token));
  }

  private setHttpOptions(token) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };
  }

  getAuction(id: number): Observable<AuctionDetail> {
    const url = `${this.auctionsUrl}/get/${id}`;
    return this.http.get<AuctionDetail>(url, this.httpOptions).pipe(
      catchError(this.handleError<AuctionDetail>(`getAuction id=${id}`))
    );
  }

  getAuctions(): Observable<Auction[]> {
    return this.http.get<Auction[]>(this.auctionsUrl + '/all');
  }

  addAuction(auctionDetail: AuctionDetail): Observable<any> {
    return this.http.post(this.auctionsUrl + '/add', auctionDetail, this.httpOptions).pipe(
      catchError(this.handleError<any>('saveAuction'))
    );
  }

  getAuctionsByName(name: string): Observable<Auction[]> {
    return this.http.get<Auction[]>(`api/search/sale/${name}`);
  }

  /* GET heroes whose name contains search term */
  searchAuction(term: string): Observable<Auction[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Auction[]>(`api/search/${term}`).pipe(
      catchError(this.handleError<Auction[]>('searchAuctions', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
