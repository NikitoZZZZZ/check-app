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
  url = '/load';

  constructor(private httpService: HttpService) {
  }

  submit(checkData) {
    const params = new URLSearchParams();
    params.set('fdocumentn', checkData.fdn);
    params.set('fdriven', checkData.fn);
    params.set('fs', checkData.fs);
    this.httpService.postData(params.toString(), this.url)
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
