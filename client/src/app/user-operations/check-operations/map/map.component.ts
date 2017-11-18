import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  lat: number = 59.929428;
  lng: number = 30.362017;

  markerlat: number = 0;
  markerlng: number = 0;
  constructor() { }

  ngOnInit() {
  }

  setMarkerEvent(lat: number,lng: number){
    this.markerlat=lat;
    this.markerlng=lng;
  }

}
