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
  place: Place = new Place();
  checkDone = false;
  placeDone = false;
  checkUrl = '/api/receipts';
  placeUrl = '/api/places';

  constructor(private httpService: HttpService) {
  }

  submit(postCheckData, addCF, addPF) {
    postCheckData.shortPlace = new ShortPlace();
    postCheckData.shortPlace.name = this.place.name;
    postCheckData.shortPlace.coords = this.place.coords;
    postCheckData.shortPlace.id = this.place.id;
    this.httpService.postBody(postCheckData, this.checkUrl)
      .subscribe((data) => {
          this.addPlace(addPF);
          this.clearPlace();
          addCF.reset();
          this.placeDone = false;
          addPF.reset();
          this.checkDone = true;
        },
        error => {
          console.log(error);
        });
  }

  addPlace(addPF) {
    this.place.coords = new Coords(59.929428,30.362019);
    this.place.numOfChecks = 0;
    this.httpService.postBody(JSON.stringify(this.place), this.placeUrl)
      .map(resp => resp.json() as Place)
      .subscribe((data) => {
          this.placeDone = true;
          this.place = data;
        },
        error => {
          console.log(JSON.stringify(error));
        });
  }

  clearPlace() {
    this.place.numOfChecks = 0;
    this.place.coords = null;
    this.place.id = null;
  }

  ngOnInit() {
  }

}
