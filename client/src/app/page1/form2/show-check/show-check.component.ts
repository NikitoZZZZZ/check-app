import {Component, Input, OnInit} from '@angular/core';
import {GetCheckData} from '../../../checkData/get-check-data';

@Component({
  selector: 'app-show-check',
  templateUrl: './show-check.component.html',
  styleUrls: ['./show-check.component.css']
})
export class ShowCheckComponent implements OnInit {

  @Input() check: GetCheckData;
  showCheck: boolean;

  constructor() { }


  ngOnInit() {
  }

}
