import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/from';
import 'rxjs/add/operator/map';

@Injectable()
export class InstructService{
  url:string = 'http://localhost:8081/bc/submit';

  constructor(private http:Http){
  }

  post(info:string){
    return this.http.get(this.url+'?info='+info).map((r:Response) => r.json());
  }

}