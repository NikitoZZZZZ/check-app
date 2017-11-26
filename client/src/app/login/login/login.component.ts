import { Component, OnInit } from '@angular/core';
import {FullUser} from "../../user-operations/checkData/full-user";
import {HttpService} from "../../services/httpService/http.service";
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [HttpService]
})
export class LoginComponent implements OnInit {
  fullUser: FullUser = new FullUser();
  done = false;
  loginUrl = '/login';
  registerUrl = '/register';

  constructor(private httpService: HttpService,
              private router: Router) { }

  submit(info) {
    const params = new URLSearchParams();
    params.set('username', info.login);
    params.set('password', info.pwd);
    this.httpService.postData(params.toString(), this.loginUrl)
    .subscribe((data) => {
      this.done = true;
      this.router.navigate(['/user-operations/check-operations/show-check']);
    },
      error => {
        console.log(error);
      });
    if(this.done) {
      this.router.navigate(['/user-operations/check-operations/show-check']);
    }
  }

  signup(fullUser) {
    this.httpService.postBody(fullUser, this.registerUrl)
      .subscribe((data) => {
          this.done = true;
        },
        error => {
          console.log(error);
        });
    if(this.done) {
      this.router.navigate(['/user-operations/check-operations/show-check']);
    }
  }

  ngOnInit() {
  }

}
