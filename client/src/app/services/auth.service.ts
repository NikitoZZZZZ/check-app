import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class AuthService {
  au = new BehaviorSubject(false);
  data = this.au.asObservable();

  constructor() { }

  change() {
    this.au.next(!this.au.value);
  }
}
