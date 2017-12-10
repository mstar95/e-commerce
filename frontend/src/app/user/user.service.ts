import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {AuthenticationService} from '../auth/authentication.service';
import {CreateUser} from './createUser';
import {catchError} from 'rxjs/operators';
import {Subject} from "rxjs/Subject";
import * as url from "url";

@Injectable()
export class UserService {

  private userUrl = 'api/user';  // URL to web api
  private userSubject = new Subject<User>();
  private userObservable$ = this.userSubject.asObservable();

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    })
  };

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  getCurrentUser(): Observable<User> {
    return this.userObservable$;
  }

  triggerGetUser() {
    const url = `${this.userUrl}/current`;
    this.http.get<User>(url, this.httpOptions)
      .subscribe(user => this.userSubject.next(user),
        () => this.userSubject.next(null));
  }

  createUser(createUser: CreateUser): Observable<any> {
    return this.http.post(this.userUrl + '/create', createUser, this.httpOptions);
  }

  refreshToken() {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.authenticationService.getToken()
      })
    };
  }

}
