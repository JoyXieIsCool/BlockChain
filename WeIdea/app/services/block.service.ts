import { Injectable } from '@angular/core';
import { Block } from '../data/block.ts';
import { Http,Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/observable/from';
import 'rxjs/add/operator/map';

const blocks: Block[]= [{
  hash: '123',
  transctions: ['REQB_A_B_100_20150623080020111','RESB_B_A_100_20150623080020111'],
},{
  hash: '124',
  transctions: ['REQB_A_B_100_20150623080020111','RESB_B_A_100_20150623080020111'],
}]

@Injectable()
export class BlockService{
  blocks = blocks;

  url:string = 'http://localhost:8081/bc/queryBlock';

  constructor(private http:Http){}

  get(cur:number){
    return this.http.get(this.url+'?currentBlock='+cur).map((r:Response)=>r.json());
    // return Observable.from<Block[]>(this.blocks);
  }

  post(){

  }
}