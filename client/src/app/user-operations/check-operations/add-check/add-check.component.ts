import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../../services/httpService/http.service';
import {PostCheckData} from '../../checkData/post-check-data';
import {Place} from '../../placeData/place';
import {Coords} from '../../placeData/coords';
import {ShortPlace} from '../../placeData/short-place';


@Component({
  selector: 'app-add-check',
  providers: [HttpService],
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

  constructor(private httpService: HttpService) {
  }

  submit(postCheckData, addCF, addPF) {
    postCheckData.shortPlace = new ShortPlace();
    postCheckData.shortPlace.name = this.newPlace.name;
    postCheckData.shortPlace.coords = this.newPlace.coords;
    postCheckData.shortPlace.id = this.newPlace.id;
    this.httpService.postBody(postCheckData, this.checkUrl)
      .subscribe((data) => {
          addCF.reset();
          this.checkDone = true;
          this.addPlace(addPF,true);
        },
        error => {
          console.log(error);
        });
  }

  addPlace(addPF,isClear) {
    this.newPlace.coords = new Coords(59.929428,30.362019);
    this.newPlace.numOfChecks = 0;
    this.httpService.postBody(JSON.stringify(this.newPlace), this.placeUrl)
      .map(resp => resp.json() as Place)
      .subscribe((data) => {
          if (!isClear) {
            this.newPlace = data;
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
    this.currentPlace=event;
  }
}
