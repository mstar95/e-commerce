import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from '../auth/authentication.service';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';

@Injectable()
export class ImageService {

  private imageUrl = 'api/image';  // URL to web api

  private httpOptions = {
    headers: new HttpHeaders({
      'Authorization': 'Bearer ' + this.authenticationService.getToken()
    })
  };

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  get(id: number): Observable<any> {
    return this.http.get<any>(this.imageUrl, this.httpOptions);
  }

  save(image: any): Observable<any> {
    if (!image) {
      return  of(null);
    }
    const input = new FormData();
    input.append('file', image);
    return this.http.post(this.imageUrl, input, this.httpOptions);
  }

}
