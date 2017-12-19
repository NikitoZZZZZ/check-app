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
  urlAll = '/api/stats/all';
  totalChecks = '';
  minTotalSum = '';
  maxTotalSum = '';
  medTotalSum = '';
  totalChecksAll = '';
  totalSumAll = '';
  checkData: GetCheckData[];
  checkDataAll: GetCheckData[];

  constructor(private statsService: StatsService,
              private proc: MessageProcessingService) { }

  ngOnInit() {
    this.getChecksInfo();
  }

  getChecksInfo() {
    this.statsService.getChecks(this.url,null)
      .subscribe((data) => {
        this.checkData = data;
        this.countChecks();
        this.sortChecks();
        this.findMinAndMax();
        this.findMedian();
      },
      error => {
        this.proc.showMessage(error);
      });
    this.statsService.getChecks(this.urlAll,null)
      .subscribe((data) => {
        this.checkDataAll = data;
        this.countAllChecks();
        this.countSumAll();
      },
        error => {
          this.proc.showMessage(error);
      })
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

  findMedian() {
    if (+this.totalChecks % 2 == 1) {
      this.medTotalSum = <string>this.checkData[Math.floor(+this.totalChecks / 2)].totalSum.toString();
    } else {
      this.medTotalSum = <string>((this.checkData[+this.totalChecks / 2].totalSum +
        this.checkData[+this.totalChecks / 2 - 1].totalSum) / 2).toString();
    }
  }

  countAllChecks() {
    this.totalChecksAll = <string>this.checkDataAll.length.toString();
  }

  countSumAll() {
    var sum = this.checkDataAll.reduce((a,b) => a + b.totalSum, 0);
    this.totalSumAll = sum.toString();
  }

}


