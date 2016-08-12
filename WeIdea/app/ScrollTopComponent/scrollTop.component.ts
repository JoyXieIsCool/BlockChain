import { Component,
         ElementRef } from '@angular/core';

@Component({
  selector: 'scroll-to-top';
  templateUrl: 'app/ScrollTopComponent/scrollTop.component.html';
  styleUrls: ['app/ScrollTopComponent/scrollTop.component.css'];
  providers: [{provide:Window, useValue:window}]
})

export class ScrollTopComponent{
  isShow:Boolean;

  constructor(private window: Window,
              public element: ElementRef){
    document.addEventListener('scroll', ()=>{
      if( document.body.scrollTop > 300){
        this.isShow = true;
      }else{
        this.isShow = false;
      }
    })    
  }

  backToTop(){
    window.scrollTo(0,0);
  }

}

