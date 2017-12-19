import { Component, OnInit } from '@angular/core';
import { StatsService } from "../../services/statsService/stats.service";
import {MessageProcessingService} from "../../services/messageService/message.processing.service";

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
  medTotalSum = '';
  totalChecksAll = '';
  totalSumAll = '';

  constructor(private statsService: StatsService,
              private proc: MessageProcessingService) { }

  ngOnInit() {
    this.getChecksInfo();
  }

  getChecksInfo() {
    this.statsService.getChecks(this.url,null)
      .subscribe((data) => {
        this.totalChecks = data.totalChecks;
        this.minTotalSum = data.minTotalSum;
        this.maxTotalSum = data.maxTotalSum;
        this.medTotalSum = data.medTotalSum;
        this.totalChecksAll = data.totalChecksAll;
        this.totalSumAll = data.totalSumAll;
      },
      error => {
        this.proc.showMessage(error);
      });
  }

}


