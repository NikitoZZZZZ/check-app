
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

  markerLat: number = 0;
  markerLng: number = 0;

  @Output() coords: EventEmitter<Coords>;

  @Input() radius: number = 0;

  marker: Coords;

  constructor(private httpService: HttpService) {
    this.coords = new EventEmitter<Coords>();
  }

  ngOnInit() {
  }

  setMarkerEvent(coord){
    this.markerLat=coord.lat;
    this.markerLng=coord.lng;
    this.marker=coord;
    this.coords.emit(this.marker);
  }


  getPlaces(){
    this.coords.emit(this.marker);
  }


}
