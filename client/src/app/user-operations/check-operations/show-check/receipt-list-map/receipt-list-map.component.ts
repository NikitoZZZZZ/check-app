import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import {ShortPlace} from "../../../placeData/short-place";
import {Coords} from "../../../placeData/coords";
import {GetCheckData} from "../../../checkData/get-check-data";
import {CheckService} from "../../../../services/checkService/check.service";

@Component({
  selector: 'app-receipt-list-map',
  templateUrl: './receipt-list-map.component.html',
  styleUrls: ['./receipt-list-map.component.css'],
  providers: [CheckService]
})
export class ReceiptListMapComponent implements OnInit {

  radius: number;
  getCheckPlaces: ShortPlace[] = [];
  @Output() getReceipts: EventEmitter<GetCheckData[]>;
  coords: Coords;
  url = '/api/receipts/places';
  urlPlace = '/api/receipts';

  constructor(private сheckService: CheckService) {
    this.coords = new Coords();
    this.getReceipts = new EventEmitter<GetCheckData[]>();
  }

  ngOnInit() {
    let rad = localStorage.getItem("radius");
    if (rad != null)
      this.radius = JSON.parse(rad);
    else this.radius = 1000;
    localStorage.setItem("radius", this.radius.toString());
  }

  getCoords(event: Coords) {
    this.coords=event;
    if (event != null && event != {} ) {
      localStorage.setItem("coords", JSON.stringify(this.coords));
      this.getPlaces();
    }
  }

  checkPlace(place: ShortPlace) {
    place.selected = !place.selected;
  }


  getPlaces() {
    const params = new URLSearchParams();
    params.set('longitude', this.coords.longitude.toString());
    params.set('latitude', this.coords.latitude.toString());
    params.set('radius', (this.radius / 1000).toString());
    localStorage.setItem("radius", this.radius.toString());
    this.сheckService.getChecks(this.url, params.toString())
      .subscribe((data) => {
        this.getCheckPlaces = [];
        data.forEach(obj => {
          this.getCheckPlaces.push(obj.shortPlace)
        });
        this.getReceipts.emit(data);
      });
  }

}
