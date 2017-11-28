import { Component } from '@angular/core';
import {Http} from "@angular/http";
import {AuthService} from "./services/auth.service";
import {Subscribable} from "rxjs/Observable";
import {HttpService} from "./services/httpService/http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AuthService, HttpService]
})
export class AppComponent {
  title = 'Login';
  authenticated = false;

  constructor(private http: Http,
              private auth: AuthService,
              private router: Router) {
    auth.data.subscribe(value => {
      this.authenticated = value;
    });
  }

  logout() {
    this.http.post('/logout', {})
      .subscribe(data => {
        this.auth.change();
        this.router.navigate(['/']);
      }),
        error => {
          console.log(error);
        }
  }

  isAuthenticated() {
    return this.authenticated;
  }
}
