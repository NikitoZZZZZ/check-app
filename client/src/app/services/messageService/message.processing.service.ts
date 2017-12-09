import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class MessageProcessingService {
  message = new BehaviorSubject<string>('');
  data = this.message.asObservable();

  constructor() { }

  change(message : string) {
    this.message.next(message);
  }
}
