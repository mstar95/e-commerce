import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuctionDetail} from './auctionDetail';
import {AuthenticationService} from '../auth/authentication.service';

@Injectable()
export class PaymentService {

  private paymentUrl = 'api/payment';  // URL to web api

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '
    })
  };

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
    this.authenticationService.getTokenObs()
      .subscribe(token => this.setHttpOptions(token));
  }

  buy(id: number): Observable<AuctionDetail> {
    const url = `${this.paymentUrl}/${id}/buy}`;
    return this.http.post<AuctionDetail>(url, this.httpOptions);
  }

  bid(id: number, amount: number): Observable<AuctionDetail> {
    const url = `${this.paymentUrl}/${id}/bid`;
    return this.http.post<AuctionDetail>(url, {auctionId: id, amount: amount}, this.httpOptions);
  }

  chargePoints(amount: number) {
    const url = `${this.paymentUrl}/charge`;
    return this.http.post(url, {amount: amount}, this.httpOptions);
  }

  private setHttpOptions(token) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      })
    };
  }

}
