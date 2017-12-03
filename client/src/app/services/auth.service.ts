import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {HttpService} from "./httpService/http.service";
import 'rxjs/add/operator/delay';

@Injectable()
export class AuthService {

  au = new BehaviorSubject<boolean>(this.hasToken());
  data = this.au.asObservable();
  providers: [HttpService];

  constructor(private http: HttpService) { }

  change() {
    this.au.next(!this.au.value);
  }

  check() {
    this.http.getData('/api/users/current', null)
      .subscribe(resp => {
        if (resp.json().username != 'anonymousUser') {
          this.au.next(true);
        }
      });
  }

  private hasToken() : boolean {
    return !!localStorage.getItem('token');
  }
}
