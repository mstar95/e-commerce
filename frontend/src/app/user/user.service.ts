import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {AuthenticationService} from '../auth/authentication.service';
import {CreateUser} from './createUser';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class UserService {

  private userUrl = 'api/user';  // URL to web api
  private userSubject = new BehaviorSubject<User>(null);
  private userObservable$ = this.userSubject.asObservable();

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

  getCurrentUser(): Observable<User> {
    return this.userObservable$;
  }

  triggerGetUser() {
    this.http.get<User>(`${this.userUrl}/current`, this.httpOptions)
      .subscribe(user => this.userSubject.next(user),
        () => this.userSubject.next(null));
  }

  createUser(createUser: CreateUser): Observable<any> {
    return this.http.post(`${this.userUrl}/create`, createUser, this.httpOptions);
  }

}
