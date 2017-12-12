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
  emptyData: boolean;
  numberOfSelectedNumbers: number;

  constructor(private httpService: HttpService) {
  }

  onToggle(check: GetCheckData) {
    check.selected = !check.selected;
  }
  setReceipts(event) {
    this.getCheckData = event;
    if (this.getCheckData.length == 0) {
      this.emptyData = true;
    } else {
      this.emptyData = false;
    }
  }


  ngOnInit() {

  }



}
