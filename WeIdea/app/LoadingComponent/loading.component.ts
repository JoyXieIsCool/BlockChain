import{ Component,
        animate,
        trigger,
        state,
        transition,
        keyframes,
        style } from '@angular/core';
import{ Router } from '@angular/router';
import{ Title } from '@angular/platform-browser';

@Component({
  selector: 'my-loading',
  templateUrl: 'app/LoadingComponent/loading.component.html',
  styleUrls: ['app/LoadingComponent/loading.component.css'],
  animations: [
    trigger('logoFade', [
        transition('void => *', [
            animate('1.5s 1.5s easeInOutBack', keyframes([
              style({opacity:0, transform: 'translateY(-200px)', offset: 0}),
              style({opacity:1, transform: 'translateY(0px)', offset: 0.125}),
              style({transform: 'translateY(-100px)', offset: 0.25}),
              style({transform: 'translateY(0px)', offset: 0.375}),
              style({transform: 'translateY(-40px)', offset: 0.5}),
              style({transform: 'translateY(0px)', offset: 0.625}),
              style({transform: 'translateY(-20px)', offset: 0.75}),
              style({transform: 'translateY(0px)', offset: 1}),
              ]))
          ])

      ])
  ]
})

export class LoadingComponent {
  constructor(
    private router: Router,
    private title: Title
    ){
    console.log(title.getTitle());
    let tmp = 'WeLoading';
    let i = 0;
    setInterval(()=>{
      if(i < 3){
        tmp += '.';
        i++;
      } else {
        tmp = 'WeLoading';
        i = 0;
      }
      title.setTitle(tmp);
    }, 1000)
  }
}
