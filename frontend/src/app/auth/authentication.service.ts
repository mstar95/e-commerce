import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Subject} from "rxjs/Subject";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class AuthenticationService {
  private authUrl = 'api/auth';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private loginSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
  private loginObservable$ = this.loginSubject.asObservable();
  private tokenSubject = new BehaviorSubject<string>(this.getToken());
  private tokenObservable$ = this.tokenSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<boolean> {
    const body = { username: username, password: password };
    return this.http.post(this.authUrl, JSON.stringify(body), {headers: this.headers})
      .map((response: any ) => {

        const token = response.token;
        if (token) {
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
          this.tokenSubject.next(this.getToken());
          this.loginSubject.next(true);
          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      }).catch((error: any) => Observable.throw(error.message || 'Server error'));
  }

  getLoginObservalbe() {
    return this.loginObservable$;
  }

  getTokenObs() {
    return this.tokenObservable$;
  }

  getToken(): string {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token : '';
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.loginSubject.next(false);
    this.tokenSubject.next(this.getToken());
  }

  isLoggedIn(): boolean {
    const token: String = this.getToken();
    return token && token.length > 0;
  }
}
