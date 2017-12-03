import { Injectable } from '@angular/core';
import {CanActivateChild, Router} from "@angular/router";
import {AuthService} from "../../auth.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class LoginGuardChildService implements CanActivateChild {
  authenticated: boolean;

  constructor(private auth: AuthService,
              private router: Router) {
    this.auth.data.subscribe(value => {
      this.authenticated = value;
    });
  }

  canActivateChild() : Observable<boolean> | boolean {
    if (!this.checkIfLoggedIn()) {
      this.router.navigate(['/']);
    }
    return this.checkIfLoggedIn();
  }

  private checkIfLoggedIn(): Observable<boolean> | boolean {
    return this.authenticated;
  }
}
