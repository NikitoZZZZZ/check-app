import {Component} from '@angular/core';
import {AuthService} from "./services/authService/auth.service";
import {HttpService} from "./services/httpService/http.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpService]
})
export class AppComponent {
  loginUrl = '/login';
  logoutUrl = '/logout';
  title = 'Войти';
  authenticated = false;
  sub: Subscription;

  constructor(private http: HttpService,
              private auth: AuthService,
              private router: Router) {

    this.sub = auth.data.subscribe(value => {
      this.authenticated = value;
    });
  }

  logout() {
    this.auth.logout(this.logoutUrl)
      .subscribe(data => {
        this.router.navigate(['/']);
      }),
        error => {
          console.log(error);
        }
  }

  isAuthenticated() {
    return this.authenticated;
  }

  ngOnInit() {
    this.auth.check();
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
