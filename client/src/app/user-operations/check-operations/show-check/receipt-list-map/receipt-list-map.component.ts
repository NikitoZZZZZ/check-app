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

  radius: number;
  getCheckPlaces: ShortPlace[] = [];
  @Output() getReceipts: EventEmitter<GetCheckData[]>;
  @Output() numberOfSelectedReceipts: EventEmitter<number>;
  countSelected: number;
  coords: Coords;
  url = '/api/receipts/places';
  urlPlace = '/api/receipts';

  constructor(private httpService: HttpService) {
    this.coords = new Coords();
    this.getReceipts = new EventEmitter<GetCheckData[]>();
    this.numberOfSelectedReceipts = new EventEmitter<number>();
    this.countSelected = 0;
  }

  ngOnInit() {
    let rad = localStorage.getItem("radius");
    if (rad != null)
      this.radius = JSON.parse(rad);
    else this.radius = 1000;
    localStorage.setItem("radius", this.radius.toString());
  }

  getCoords(event: Coords) {
    this.coords = event;
    if (event != null && event != {}) {
      localStorage.setItem("coords", JSON.stringify(this.coords));
      this.getPlaces();
    }
  }

  checkPlace(place: ShortPlace) {
    place.selected = !place.selected;
    if (place.selected) this.countSelected++;
    else this.countSelected--;
    this.numberOfSelectedReceipts.emit(this.countSelected);
  }


  getPlaces() {
    const params = new URLSearchParams();
    params.set('longitude', this.coords.longitude.toString());
    params.set('latitude', this.coords.latitude.toString());
    params.set('radius', (this.radius / 1000).toString());
    localStorage.setItem("radius", this.radius.toString());
    this.httpService.getData(this.url, params.toString())
      .map(resp => resp.json() as GetCheckData[])
      .subscribe((data) => {
        this.getCheckPlaces = [];
        data.forEach(obj => {
          this.getCheckPlaces.push(obj.shortPlace)
        });
        this.numberOfSelectedReceipts.emit(0);
        this.getReceipts.emit(data);
      });
  }

}
