import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpService} from "../../../services/httpService/http.service";
import {Coords} from "../../placeData/coords";


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {


  lat: number = 59.929428;
  lng: number = 30.362017;

  @Output() coords: EventEmitter<Coords>;

  @Input() radius: number = 0;

  marker: Coords;

  constructor(private httpService: HttpService) {
    this.coords = new EventEmitter<Coords>();
  }

  ngOnInit() {
    this.marker=new Coords();
    let geoPoint = localStorage.getItem("coords");
    if (geoPoint != "{}") {
      this.marker = JSON.parse(geoPoint);
    }
    else {
      this.marker.longitude = this.lat;
      this.marker.latitude = this.lng;
    }
    this.getPlaces();
  }

  setMarkerEvent(coord) {
    this.marker.longitude  = coord.lat;
    this.marker.latitude = coord.lng;
    this.coords.emit(this.marker);
  }


  getPlaces() {
    this.coords.emit(this.marker);
  }


}
