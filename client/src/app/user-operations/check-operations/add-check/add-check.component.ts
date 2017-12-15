import {Component, OnInit} from '@angular/core';
import {PlaceService} from '../../../services/placeService/place.service';
import {CheckService} from '../../../services/checkService/check.service';
import {PostCheckData} from '../../checkData/post-check-data';
import {Place} from '../../placeData/place';
import {Coords} from '../../placeData/coords';
import {ShortPlace} from '../../placeData/short-place';
import {SharedPlaceService} from "../../../services/sharedPlace/shared-place.service";


@Component({
  selector: 'app-add-check',
  providers: [PlaceService,CheckService],
  templateUrl: './add-check.component.html',
  styleUrls: ['./add-check.component.css']
})


export class AddCheckComponent implements OnInit {
  postCheckData: PostCheckData = new PostCheckData();
  newPlace: Place = new Place();
  currentPlace: Place;
  currentCoords: Coords;
  checkDone = false;
  placeDone = false;
  checkUrl = '/api/receipts';
  placeUrl = '/api/places';

  constructor(private placeService: PlaceService,
              private checkService: CheckService,
              private sharedService: SharedPlaceService) {
  }

  submit(postCheckData, addCF, addPF) {
    postCheckData.shortPlace = new ShortPlace();
    postCheckData.shortPlace.name = this.currentPlace.name;
    postCheckData.shortPlace.coords = this.currentPlace.coords;
    postCheckData.shortPlace.id = this.currentPlace.id;
    this.checkService.createCheck(postCheckData, this.checkUrl)
      .subscribe((data) => {
          addCF.reset();
          this.checkDone = false;
          this.addPlace(addPF,true);
          this.sharedService.setPlace(null);
        },
        error => {
          console.log(error);
        });
  }

  addPlace(addPF,isClear) {
    if (!isClear) {
      this.newPlace.coords = this.currentCoords;
      this.newPlace.numOfChecks = 0;
    } else {
      this.newPlace = this.currentPlace;
    }
    this.placeService.createPlace(JSON.stringify(this.newPlace), this.placeUrl)
      .map(res => res.json() as Place)
      .subscribe((data) => {
          if (!isClear) {
            this.newPlace = data;
            this.checkDone = true;
            this.sharedService.setPlace(data);
            this.currentPlace = this.newPlace;
          } else {
            this.clearPlace();
            addPF.reset();
          }
          this.placeDone = !isClear;
        },
        error => {
          console.log(JSON.stringify(error));
        });
  }

  clearPlace() {
    this.newPlace.numOfChecks = 0;
    this.newPlace.coords = null;
    this.newPlace.id = null;
  }

  ngOnInit() {
  }

  setCurrentCoords(event){
    this.currentCoords=event;
  }

  setCurrentPlace(event){
    this.currentPlace = event;
    this.checkDone = true;
  }
}
