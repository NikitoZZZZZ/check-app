import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../services/httpService/http.service';
import {GetCheckData} from '../../checkData/get-check-data';


@Component({
  selector: 'app-form2',
  templateUrl: './form2.component.html',
  styleUrls: ['./form2.component.css'],
  providers: [HttpService]
})
export class Form2Component implements OnInit {

  getCheckData: GetCheckData[];
  done = false;
  showCheck: boolean;
  /*url = '/assets/data.json';*/
  url = 'http://localhost:8090/getAll';
  constructor(private httpService: HttpService) {

  }
  onToggle(check: GetCheckData) {
    check.selected = ! check.selected;
  }


  /*
 getCheckById(id: string) {
     this.httpService.getDataById(this.url, id)
       .map(resp => resp.json().data as GetCheckData[])
       .subscribe((data) => {
         console.log(data);
       });
}
*/

  init() {
    this.httpService.getData(this.url)
      .map(resp => resp.json().data as GetCheckData[])
      .subscribe((data) => {
        console.log(data);
        this.getCheckData = data;
      });
  }

  ngOnInit() {
    this.init();
  }

}
