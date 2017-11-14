import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-receipt-list-map',
  templateUrl: './receipt-list-map.component.html',
  styleUrls: ['./receipt-list-map.component.css']
})
export class ReceiptListMapComponent implements OnInit {

  constructor() { }
  radius: string;
  ngOnInit() {
    this.radius="0";
  }

  checkValue(){
    if (this.radius==="") this.radius="0";
  }
}
