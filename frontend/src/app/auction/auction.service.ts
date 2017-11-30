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
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    })
  };

  constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService) { }

  getAuction(id: number): Observable<Auction> {
    const url = `${this.auctionsUrl}/get/${id}`;
    return this.http.get<Auction>(url).pipe(
      catchError(this.handleError<Auction>(`getAuction id=${id}`))
    );
  }

  getAuctions(): Observable<Auction[]> {
    return this.http.get<Auction[]>(this.auctionsUrl + '/all');
  }

  addAuction (auctionDetail: AuctionDetail): Observable<any> {
    return this.http.post(this.auctionsUrl + '/add', auctionDetail, this.httpOptions).pipe(
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
      catchError(this.handleError<Auction[]>('searchAuctions', []))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
