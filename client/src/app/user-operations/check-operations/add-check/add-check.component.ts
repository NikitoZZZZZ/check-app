import {Component, OnInit} from '@angular/core';
import {HttpService} from '../../../services/httpService/http.service';
import {PostCheckData} from '../../checkData/post-check-data';
import {MessageProcessingService} from "../../../services/messageService/message.processing.service";


@Component({
  selector: 'app-form1',
  providers: [HttpService],
  templateUrl: './add-check.component.html',
  styleUrls: ['./add-check.component.css']
})


export class AddCheckComponent implements OnInit {
  postCheckData: PostCheckData = new PostCheckData();
  done = false;
  url = '/api/receipts';

  constructor(private httpService: HttpService,
              private proc: MessageProcessingService) {
  }

  submit(postCheckData, addCF) {
    this.httpService.postBody(postCheckData, this.url)
      .subscribe((data) => {
          addCF.reset();
          this.done = true;
          console.log('ekkekekekekeke');
          if (data.status == 201) {
            this.proc.change("Receipt added successfully");
          }
        },
        error => {
          this.proc.change("Wrong data");
        });
  }

  ngOnInit() {
  }

}
