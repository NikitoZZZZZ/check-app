import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Place} from "../../../../placeData/place";
import {Coords} from "../../../../placeData/coords";
import {HttpService} from "../../../../../services/httpService/http.service";

@Component({
  selector: 'app-place-list',
  templateUrl: './place-list.component.html',
  styleUrls: ['./place-list.component.css']
})
export class PlaceListComponent implements OnInit {

  radius: number;
  places: Place[];
  url = '/api/places';
  coords: Coords;
  @Output() currentCords: EventEmitter<Coords>;
  @Output() currentPlace: EventEmitter<Place>;

  constructor(private httpService: HttpService) {
    this.coords = new Coords();
    this.currentCords= new EventEmitter<Coords>();
    this.currentPlace= new EventEmitter<Place>();
  }

  ngOnInit() {
    this.radius = 200;
  }

  getCoords(event) {
    this.coords = event;
    this.getPlaces();
    this.currentCords.emit(this.coords);
  }

  getPlaces() {
    const params = new URLSearchParams();
    params.set('longitude', this.coords.longitude.toString());
    params.set('latitude', this.coords.latitude.toString());
    this.httpService.getData(this.url, params.toString())
      .map(resp => resp.json() as Place[])
      .subscribe((data) => {
        this.places = data;
      });
  }

  getCurrentPlace(place){
    this.currentPlace.emit(place);
  }


}