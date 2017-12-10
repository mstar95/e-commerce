import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from '../auth/authentication.service';
import {Observable} from 'rxjs/Observable';
import {Message} from "./message";

@Injectable()
export class MessageService {

  private messageUrl = 'api/message';  // URL to web api

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    })
  };

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  getAll(): Observable<Message[]> {
    return this.http.get<any>(this.messageUrl, this.httpOptions);
  }

  markSeen(): Observable<any> {
    return this.http.get(this.messageUrl + '/seen', this.httpOptions);
  }


}
