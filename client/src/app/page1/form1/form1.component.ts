import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/httpService/http.service';
import {PostCheckData} from '../../checkData/post-check-data';


@Component({
  selector: 'app-form1',
  providers: [HttpService],
  templateUrl: './form1.component.html',
  styleUrls: ['./form1.component.css']
})


export class Form1Component implements OnInit {
  postCheckData: PostCheckData = new PostCheckData();
  done = false;
  url = 'http://localhost:8090/load';

  constructor(private httpService: HttpService) {
  }

  submit(checkData) {
    this.httpService.postData(checkData, this.url)
      .subscribe((data) => {
          this.done = true;
        },
        error => {
          console.log(error);
        });
  }

  ngOnInit() {
  }

}
