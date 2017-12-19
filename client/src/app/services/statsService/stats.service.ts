import {Injectable} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {GetCheckData} from "../../user-operations/checkData/get-check-data";


@Injectable()
export class StatsService {

  constructor(private http: Http) { }

  getChecks(url: string, params: any) {
    return this.http.get(url, {params})
      .map(resp => resp.json() as GetCheckData[]);
  }

}
