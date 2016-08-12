import { Component,
         OnInit,
         OnDestroy } from '@angular/core';
import { BlockService } from '../services/block.service';
import { RoleService } from '../services/role.service';
import { NotifyService } from '../services/notify.service';
import { DisplayZoneComponent } from '../DisplayZoneComponent/displayZone.component';
import { RolesComponent } from '../RolesComponent/roles.component';
import { TerminalComponent } from '../TerminalComponent/terminal.component';
import { ScrollTopComponent } from '../ScrollTopComponent/scrollTop.component';
import { CdatePipe } from '../pipes/cdate.pipe';
import { Instruct2Action } from '../pipes/instruct2Action.pipe' 

console.log(NotifyService)
@Component({
  selector    : 'dashborad',
  templateUrl : 'app/DashboardComponent/dashboard.component.html',
  styleUrls   : ['app/DashboardComponent/dashboard.component.css'],
  providers   : [BlockService, RoleService, NotifyService],
  directives  : [DisplayZoneComponent, RolesComponent, TerminalComponent, ScrollTopComponent],
  pipes       : [CdatePipe, Instruct2Action]
})

export class DashboardComponent implements OnInit, OnDestroy{
  roles        : [];
  mine         : string;
  timer        : any;
  messageTimer : any;
  needComfirm  : Boolean = false;
  message      : any = [];
  isSuccess    : Boolean;
  during       : number = 1000;
  oldRoles     : number = 0;
  newRoles     : number = 0;

  constructor(
    private blockService: BlockService,
    private roleService: RoleService,
    private notifyService: NotifyService
    ){}


  ngOnInit(){
    this.updateRoles();
    this.getMessage();
  }

  ngOnDestroy(){

  }

  yeap(){
    this.newRoles = 0;
    this.oldRoles = this.roles.length;
  }

  updateRoles(){
    let that = this;
    this.roleService.get().subscribe(data => {
      that.roles    = data.nodes;
      that.mine     = data.me;
      that.newRoles = that.roles.length - that.oldRoles;
    });
    if(!!this.timer){
      clearTimeout(this.timer);
    }

    this.timer = setTimeout(()=>{
      that.updateRoles();
    }, this.during * 2)
  }

  getMessage(){
    let that = this;
    this.notifyService.get().subscribe({
      next(value){
        if(!!value["alert"] && value["alert"] == '1'){
          that.needComfirm = true;
          let tmp = value.msg.split('_');
          let [instruct, from, to, amount, date] = tmp;
          let o = {
            instruct, from, to, date,
            amount : +amount
          };
          that.message = o;
        }
      }
    })
    if(!!this.messageTimer){
      clearTimeout(this.messageTimer);
    }
    this.messageTimer = setTimeout(()=>{that.getMessage();}, 1000)
  }

  featBack(act:Boolean){
    let that = this;
    return that.notifyService.post(act).subscribe({
      next(value){
        value.status == '1' ?　alert('操作成功！') : alert('操作失败！');
        that.needComfirm = false;
      }
    })
  }
}