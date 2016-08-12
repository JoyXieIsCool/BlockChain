import { Component,
         Input,
         Output,
         EventEmitter,
         OnChange } from '@angular/core';

@Component({
  selector    : 'role-list';
  templateUrl : 'app/RolesComponent/roles.component.html';
  styleUrls   : ['app/RolesComponent/roles.component.css'];
})

export class RolesComponent implements OnChange{
  @Input()
  mine  : string;
  @Input()
  roles : [];
  @Input()
  newRoles : number;
  //这个是好玩的yeap
  @Output()
  yeap = new EventEmitter();

  clean(){
    this.yeap.emit('Hello Guy');
  }

  constructor(){}

  ngOnChange(changes){
    console.log(changes);
    console.log('change-------');
  }
}