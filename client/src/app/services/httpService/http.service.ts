import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Response, Headers, URLSearchParams} from '@angular/http';
import {PostCheckData} from '../../checkData/post-check-data';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';


@Injectable()
export class HttpService {

  constructor(private http: Http) { }

  postData(checkData: PostCheckData, url: string) {
    const headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    const params = new URLSearchParams();
    params.set('fdocumentn', checkData.fn);
    params.set('fdriven', checkData.fdn);
    params.set('fs', checkData.fs);
    return this.http.post( url , params.toString(), { headers: headers })
      .map(res => res.json())
      .catch((error: any ) => Observable.throw(error));
  }

  getData(url: string) {
    return this.http.get(url);
  }

  /*
 getDataById(url: string, id: string) {
     return this.http.get(url);
   }
*/
}
