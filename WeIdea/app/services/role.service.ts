import { Injectable } from '@angular/core';
import { Role } from '../data/role.ts';
import { Http,Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/from';
import 'rxjs/add/operator/map';

@Injectable()
export class RoleService{
  url:string = 'http://localhost:8081/bc/queryNode';

  constructor(private http:Http){}

  get(){
    return this.http.get(this.url).map((r: Response) =>r.json())
  }
}