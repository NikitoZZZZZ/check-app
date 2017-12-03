import {Component} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {HttpService} from "./services/httpService/http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpService]
})
export class AppComponent {
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
    this.http.postEmptyBody('/logout')
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
