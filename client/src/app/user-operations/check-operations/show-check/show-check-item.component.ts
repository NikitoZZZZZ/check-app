import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../../services/httpService/http.service';
import {GetCheckData} from '../../checkData/get-check-data';

@Component({
  selector: 'app-form2',
  templateUrl: './show-check-item.component.html',
  styleUrls: ['./show-check-item.component.css'],
  providers: [HttpService]
})
export class ShowCheckItemComponent implements OnInit {

  getCheckData: GetCheckData[];
  done = false;
  showCheck: boolean;
  url = '/api/receipt';
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
      .map(resp => resp.json() as GetCheckData[])
      .subscribe((data) => {
        this.getCheckData = data;
      });
  }

  ngOnInit() {
    this.init();
  }

}
