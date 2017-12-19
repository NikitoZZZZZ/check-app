import { Component, OnInit } from '@angular/core';
import { StatsService } from "../../services/statsService/stats.service";
import {MessageProcessingService} from "../../services/messageService/message.processing.service";
import {GetCheckData} from "../checkData/get-check-data";
import {ShortPlace} from "../placeData/short-place";

@Component({
  selector: 'app-page2',
  providers: [StatsService],
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.css']
})
export class Page2Component implements OnInit {
  url = '/api/stats';
  totalChecks = '';
  minTotalSum = '';
  maxTotalSum = '';
  avgTotalSum = '';
  medTotalSum = '';
  totalPlaces = '';
  mostItems = '';
  checkData: GetCheckData[];

  constructor(private statsService: StatsService,
              private proc: MessageProcessingService) { }

  ngOnInit() {
  }

  getChecksInfo() {
    this.statsService.getChecks(this.url,null)
      .subscribe((data) => {
        this.checkData = data;
        this.countChecks();
        this.sortChecks();
        this.findMinAndMax();
        this.findAverage();
        this.findMedian();
        this.findPlaces();
        this.findMostItems();
      },
      error => {
        this.proc.showMessage(error);
      });
  }

  sortChecks() {
    this.checkData.sort((a, b) => {
      return (a.totalSum < b.totalSum) ? -1 :
        (a.totalSum > b.totalSum) ? 1 : 0;
    })
  }

  countChecks() {
    this.totalChecks = <string>this.checkData.length.toString();
  }

  findMinAndMax() {
    this.minTotalSum = <string>this.checkData[0].totalSum.toString();
    this.maxTotalSum = <string>this.checkData[this.checkData.length-1].totalSum.toString();
  }

  findAverage() {
    var sum = this.checkData.reduce((a,b) => a + b.totalSum, 0);
    this.avgTotalSum = <string>(sum / +this.totalChecks).toString(10);
  }

  findMedian() {
    if (+this.totalChecks % 2 == 1) {
      this.medTotalSum = <string>this.checkData[Math.floor(+this.totalChecks / 2)].totalSum.toString();
    } else {
      this.medTotalSum = <string>((this.checkData[+this.totalChecks / 2].totalSum +
        this.checkData[+this.totalChecks / 2 - 1].totalSum) / 2).toString();
    }
  }

  findPlaces() {
    var sp = new Set<ShortPlace>();
    this.checkData.forEach(obj => {
      let found = false;
      sp.forEach(item => {
        if (item.id === obj.shortPlace.id)
          found = true;
      })
      if (!found)
        sp.add(obj.shortPlace)
    });
    this.totalPlaces = <string>sp.size.toString();
  }

  findMostItems() {
    let max = 0;
    this.checkData.forEach(obj => {
      if (obj.items.length > max)
        max = obj.items.length;
    });
    this.mostItems = <string>max.toString();
  }

}


