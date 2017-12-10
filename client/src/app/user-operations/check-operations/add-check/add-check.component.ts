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

  submit(postCheckData, addCF) {
    /*postCheckData.shortPlace = new ShortPlace();
    postCheckData.shortPlace.name = this.place.name;
    postCheckData.shortPlace.coords = new Coords(this.place.coords.latitude, this.place.coords.longitude);
    postCheckData.shortPlace.id = this.place.id;*/
    this.httpService.postBody(postCheckData, this.checkUrl)
      .subscribe((data) => {
          addCF.reset();
          this.checkDone = true;
        },
        error => {
          console.log(error);
        });
  }

  addPlace(place, addPF) {
    place.coords = new Coords(59.929428,30.362019);
    this.httpService.postBody(place, this.placeUrl)
      .subscribe((data) => {
          this.placeDone = true;
        },
        error => {
          console.log(error);
        });
  }

  ngOnInit() {
  }

}
