import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/from';
import 'rxjs/add/operator/map';


@Injectable()
export class NotifyService{
  url:string = 'http://localhost:8081/bc/queryResponse';

  constructor(private http:Http){

  }
  //查看是否有新数据
  get(){
    return this.http.get(this.url).map((r:Response)=>r.json());
  }

  //返回用户选择
  post(act:Boolean){
    return this.http.post(this.url, {isAck: (act ? 'Y' : 'N')}).map((r:Response)=>r.json());
  }
}