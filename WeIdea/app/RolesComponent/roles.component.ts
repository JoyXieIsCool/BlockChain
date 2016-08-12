import { Component,
         Input } from '@angular/core';

@Component({
  selector    : 'role-list';
  templateUrl : 'app/RolesComponent/roles.component.html';
  styleUrls   : ['app/RolesComponent/roles.component.css'];
})

export class RolesComponent {
  @Input()
  mine    : string;
  @Input()
  roles : [];

  constructor(){}

}