import { Component,
          View,
          OnInit,
          OnDestroy,
          trigger,
          state,
          style,
          transition,
          keyframes,
          animate,
          Input } from '@angular/core';
import { BlockService } from '../services/block.service';
import { CdatePipe } from '../pipes/cdate.pipe';
import { Instruct2Action } from '../pipes/instruct2Action.pipe' 

@Component({
  selector    : 'display-zone',
  templateUrl : 'app/DisplayZoneComponent/displayZone.component.html',
  styleUrls   : ['app/DisplayZoneComponent/displayZone.component.css'],
  pipes       : [CdatePipe, Instruct2Action],
  animations : [
    trigger('colorRun', [
        transition('void => *', [
            animate('1s 1s ease-in-out', keyframes([
                style({'width': '0', offset: 0}),
                style({'width': '100%', offset: 1})
              ]))
          ])
      ])
  ]
})

export class DisplayZoneComponent implements OnInit, OnDestroy{
  blocks : [] = [];
  blocksReverse : [] = [];
  timer : any;
  oldGuy : number = 0;
  newGuy : number = 0;

  @Input()
  mine:string;

  constructor(private blockService : BlockService){
  }


  ngOnInit(){
    this.updateBlocks();
    // setTimeout(()=>{
    //   this.blocks.unshift({
    //       hash: '0000',  
    //       transctions: ['REQB_A_B_100_20150623080020111','RESB_B_A_100_20150623080020111'],
    //     });
    //   console.log(blocks)
    // }, 1000)
  }

  ngOnDestroy(){

  }

  updateBlocks(){
    let that = this;
    let counter = 0;
    this.oldGuy = this.blocks.length;
    this.blockService
        .get(that.blocks.length)
        .subscribe({
          next(value){
            //查找数据，找不到就push进数组,
            //后台查找了，直接push进去就好
            console.log(value)
            console.log(Array.isArray(value.transctions))
            if(Array.isArray(value)){
              value.forEach((cur) => {
              	cur.ttransctions = cur.content.map((transction) => {
                let tmp = transction.split('_');
                let [instruct, from, to, amount, date] = tmp;
                let o = {
                  instruct, from, to, date,
                  amount : +amount
                };
                return o;
              })
              counter ++;
              //倒着插入
              that.blocks.unshift(cur);              
              })

            }
            console.log(that.blocks);
          },
          complete(){
            if(counter > 0){
              that.newGuy = counter;
            }
          }
        }); 
    if(!!that.timer){
      clearTimeout(that.timer);
    }
    that.timer = setTimeout(()=>{
      that.updateBlocks();
    }, 1000);
  }
}