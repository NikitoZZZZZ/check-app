import {Component} from '@angular/core';
import {AuthService} from "./services/authService/auth.service";
import {HttpService} from "./services/httpService/http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpService]
})
export class AppComponent {
  loginUrl = '/login';
  logoutUrl = '/logout';
  title = 'Login';
  authenticated = false;
  loggedUser = '';

  constructor(private http: HttpService,
              private auth: AuthService,
              private router: Router) {
    auth.data.subscribe(value => {
      this.authenticated = value;
    });
  }

  logout() {
    this.auth.logout(this.logoutUrl)
      .subscribe(data => {
        this.auth.change();
        localStorage.removeItem('token');
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
}
