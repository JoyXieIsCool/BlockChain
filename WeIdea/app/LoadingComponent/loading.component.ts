import{ Component,
        animate,
        trigger,
        state,
        transition,
        keyframes,
        style,
        OnInit,
        OnDestroy } from '@angular/core';
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
      ]),
    trigger('scaleImg', [
      transition('void => *',[
          animate('1.5s 1s easeInOut', keyframes([
            style({opacity:0, transform: 'scale(1.3)', offset: 0}),
            style({opacity:1, transform: 'rotate(-28deg)', offset: 0.5})
            ]))
        ])
      ]),
    trigger('fadeDiv',[
      transition('void => *',[
          animate('1.5s 1s easeInOut', keyframes([
            style({opacity:0, offset: 0}),
            style({opacity:1, offset: 1})
            ]))
        ])
      ])

  ]
})

export class LoadingComponent implements OnDestroy,OnInit{
  showLogo : Boolean = false;
  showGo : Boolean = false;

  constructor(
    private router: Router,
    private title: Title
    ){
    console.log(title.getTitle());
    let tmp = 'WeLoading';
    let i = 0;
    let setter = setInterval(()=>{
      if(i < 3){
        tmp += '.';
        i++;
      } else {
        tmp = 'WeLoading';
        i = 0;
      }
      title.setTitle(tmp);
    }, 500)

    setTimeout(()=>{
      this.showLogo = true;
      clearInterval(setter);
      title.setTitle("We借条 WeIdea");
    }, 3000)

    setTimeout(()=>{
      this.showGo = true;
    }, 4000)
  }

  ngOnInit(){
    document.body.style.overflow = "hidden";
  }

  ngOnDestroy(){
    document.body.style.overflow = "auto";
  }

  travel(){
    this.router.navigate(['dashboard'])
  }
}
