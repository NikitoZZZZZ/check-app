import {Component, Input, OnInit} from '@angular/core';
import {Place} from "../../../placeData/place";

@Component({
  selector: 'app-receipt-list-map',
  templateUrl: './receipt-list-map.component.html',
  styleUrls: ['./receipt-list-map.component.css']
})
export class ReceiptListMapComponent implements OnInit {

  constructor() { }
  radius: number;
  getCheckPlaces: Place[];
  ngOnInit() {
    this.radius=0;
  }

  getPlaces(event){
    this.getCheckPlaces=event;
  }

  checkPlace(place: Place){
    place.selected = ! place.selected;
  }
}
