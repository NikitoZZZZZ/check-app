import {Component, Input, OnInit} from '@angular/core';

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

  constructor(private httpService: HttpService) {
    this.coords = new Coords();
  }

  radius: number = 0;
  getCheckPlaces: ShortPlace[]=[];
  getReceipts: GetCheckData[];
  coords: Coords;
  url = '/api/receipts/places';
  //url = "/assets/data.json";

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
  }

  getPlaces() {
    const params = new URLSearchParams();
    params.set('longitude', this.coords.longitude.toString());
    params.set('latitude', this.coords.latitude.toString());
    params.set('radius', this.radius.toString());
    this.httpService.getData(this.url, params.toString())
      .map(resp => resp.json() as GetCheckData[])
      .subscribe((data) => {
        this.getCheckPlaces=[];
        this.getReceipts = data;
        data.forEach(obj=>{this.getCheckPlaces.push(obj.place)})

      });

  }

}
