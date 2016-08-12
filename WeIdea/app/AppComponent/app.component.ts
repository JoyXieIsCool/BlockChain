import{ Component } from '@angular/core';
import{ ROUTER_DIRECTIVES } from '@angular/router';
import{ Router } from '@angular/router';
import{ Title } from '@angular/platform-browser';

@Component({
  selector: 'my-app',
  templateUrl: 'app/AppComponent/app.component.html',
  directives: [ ROUTER_DIRECTIVES ],
  providers: [ Title ]
})

export class AppComponent{
  constructor(
    router: Router,
    title: Title){
  }
}