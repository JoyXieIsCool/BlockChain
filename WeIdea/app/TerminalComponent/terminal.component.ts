import {  Component,
          Input,
          trigger,
          state,
          style,
          transition,
          keyframes,
          animate } from '@angular/core';
import { Instruct2Action } from '../pipes/instruct2Action.pipe';
import { State2Text } from '../pipes/state2Text.pipe';
import { InstructService } from '../services/instruct.service';

@Component({
  selector    : 'terminal-board';
  templateUrl : 'app/TerminalComponent/terminal.component.html';
  styleUrls   : ['app/TerminalComponent/terminal.component.css'];
  pipes       : [State2Text];
  providers   : [InstructService];
  animations  : [
    trigger('fadeOut', [
        transition('*=>void', animate('.3s .3s ease-in-out', keyframes([
            style({offset:0, opacity: 1}),
            style({offset:1, opacity: 0})
          ])))
      ])
  ]
})

export class TerminalComponent{
  @Input()
  roles:[];

  @Input()
  mine:string;

  target: string;

  state: number;

  action: string;

  act : number;

  amount : number;

  isSuccess : Boolean;

  constructor(private instructService: InstructService){
    this.state = 0;
    this.act = 1;
  }

  getStart(){
    this.state = 1;
  }
  selectAction(act:number){
    this.act = act;
    if(act === 1){
      this.action = "1000";
    }else{
      this.action = "1002";
    }
    //进入下一个状态；
    this.state = 2;
  }
  selectTarget(){
    //进入下一个状态；
    if(!this.target) return;
    this.state = 3;
  }
  comfirm(){
    //提交
    if(!this.amount) return;
    this.state = 4;
    let str = this.action + '_' + this.mine + '_' + this.target + '_' + this.amount;
    this.instructService.post(str).subscribe(data => {
      this.isSuccess = data.status == '1' ?　true : false;
      this.state = 5;
    });
  }
  reset(){
    //重置
    this.state = 0;
    this.amount = undefined;
    this.target = undefined;
    this.act = 1;
  }

}