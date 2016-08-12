"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var platform_browser_1 = require('@angular/platform-browser');
var LoadingComponent = (function () {
    function LoadingComponent(router, title) {
        this.router = router;
        this.title = title;
        console.log(title.getTitle());
        var tmp = 'WeLoading';
        var i = 0;
        setInterval(function () {
            if (i < 3) {
                tmp += '.';
                i++;
            }
            else {
                tmp = 'WeLoading';
                i = 0;
            }
            title.setTitle(tmp);
        }, 1000);
    }
    LoadingComponent = __decorate([
        core_1.Component({
            selector: 'my-loading',
            templateUrl: 'app/LoadingComponent/loading.component.html',
            styleUrls: ['app/LoadingComponent/loading.component.css'],
            animations: [
                core_1.trigger('logoFade', [
                    core_1.transition('void => *', [
                        core_1.animate('1.5s 1.5s easeInOutBack', core_1.keyframes([
                            core_1.style({ opacity: 0, transform: 'translateY(-200px)', offset: 0 }),
                            core_1.style({ opacity: 1, transform: 'translateY(0px)', offset: 0.125 }),
                            core_1.style({ transform: 'translateY(-100px)', offset: 0.25 }),
                            core_1.style({ transform: 'translateY(0px)', offset: 0.375 }),
                            core_1.style({ transform: 'translateY(-40px)', offset: 0.5 }),
                            core_1.style({ transform: 'translateY(0px)', offset: 0.625 }),
                            core_1.style({ transform: 'translateY(-20px)', offset: 0.75 }),
                            core_1.style({ transform: 'translateY(0px)', offset: 1 }),
                        ]))
                    ])
                ])
            ]
        }), 
        __metadata('design:paramtypes', [router_1.Router, platform_browser_1.Title])
    ], LoadingComponent);
    return LoadingComponent;
}());
exports.LoadingComponent = LoadingComponent;
//# sourceMappingURL=loading.component.js.map