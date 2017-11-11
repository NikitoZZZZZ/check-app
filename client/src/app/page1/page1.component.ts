import { Component, OnInit } from '@angular/core';
import {HttpService} from '../services/httpService/http.service';

@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.css'],
  providers: [HttpService]
})

export class Page1Component implements OnInit {

  constructor() { }

  ngOnInit() {

  }
}
