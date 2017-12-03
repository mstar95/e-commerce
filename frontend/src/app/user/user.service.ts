import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import {AuthenticationService} from '../auth/authentication.service';
import {CreateUser} from './createUser';
import {catchError} from 'rxjs/operators';

@Injectable()
export class UserService {

  private userUrl = 'api/user';  // URL to web api

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
    const url = `${this.userUrl}/current`;
    return this.http.get<User>(url, this.httpOptions);
  }

  createUser(createUser: CreateUser ): Observable<any> {
    return this.http.post(this.userUrl + '/create', createUser, this.httpOptions);
  }

}
