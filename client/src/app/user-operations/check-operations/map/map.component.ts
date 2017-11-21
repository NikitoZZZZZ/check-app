import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpService} from "../../../services/httpService/http.service";
import {Place} from "../../placeData/place";


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
  providers: [HttpService]
})
export class MapComponent implements OnInit {


  lat: number = 59.929428;
  lng: number = 30.362017;

  markerlat: number = 0;
  markerlng: number = 0;

  /*url = '/api/places';*/
  url="/assets/data.json";


  @Output() places: EventEmitter<Place[]>;

  @Input() radius: number = 0;


  constructor(private httpService: HttpService) {
    this.places = new EventEmitter<Place[]>();
  }

  ngOnInit() {
  }

  setMarkerEvent(lat: number,lng: number){
    this.markerlat=lat;
    this.markerlng=lng;
  }


  getPlaces(){
    const params = new URLSearchParams();
    params.set('longitude', this.markerlng.toString());
    params.set('latitude', this.markerlat.toString());
    params.set('radius', this.radius.toString());
    this.httpService.getData(this.url,params.toString())
      .map(resp => resp.json() as Place[])
      .subscribe((data) => {
        this.places.emit(data);

      });

  }


}
