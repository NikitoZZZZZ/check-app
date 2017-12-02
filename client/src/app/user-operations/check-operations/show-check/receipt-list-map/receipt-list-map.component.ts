import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {ShortPlace} from "../../../placeData/short-place";
import {Coords} from "../../../placeData/coords";
import {HttpService} from "../../../../services/httpService/http.service";
import {GetCheckData} from "../../../checkData/get-check-data";

@Component({
  selector: 'app-receipt-list-map',
  templateUrl: './receipt-list-map.component.html',
  styleUrls: ['./receipt-list-map.component.css'],
  providers: [HttpService]
})
export class ReceiptListMapComponent implements OnInit {

  radius: number = 0;
  getCheckPlaces: ShortPlace[] = [];
  @Output() getReceipts: EventEmitter<GetCheckData[]>;
  coords: Coords;
  url = '/api/receipts/places';
  //url = "/assets/data.json";
  urlPlace = '/api/receipts';
  checked: String[]=[];

  constructor(private httpService: HttpService) {
    this.coords = new Coords();
    this.getReceipts = new EventEmitter<GetCheckData[]>();
  }

  ngOnInit() {
    this.radius = 0;
  }

  getCoords(event) {
    this.coords.longitude = event.lat;
    this.coords.latitude = event.lng;
    this.getPlaces();
  }

  checkPlace(place: ShortPlace) {
    place.selected = !place.selected;
    if (place.selected) {
      this.checked.push(place.id)
    }
    else {
      this.checked.splice(this.checked.indexOf(place.id),1);
    }
  }


  getPlaces() {
    const params = new URLSearchParams();
    params.set('longitude', this.coords.longitude.toString());
    params.set('latitude', this.coords.latitude.toString());
    params.set('radius', (this.radius / 1000).toString());
    this.httpService.getData(this.url, params.toString())
      .map(resp => resp.json() as GetCheckData[])
      .subscribe((data) => {
        this.getCheckPlaces=[];
        data.forEach(obj=>{this.getCheckPlaces.push(obj.shortPlace)});
        this.getReceipts.emit(data);
      });
  }

}
