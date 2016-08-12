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
var ScrollTopComponent = (function () {
    function ScrollTopComponent(window, element) {
        var _this = this;
        this.window = window;
        this.element = element;
        document.addEventListener('scroll', function () {
            if (document.body.scrollTop > 300) {
                _this.isShow = true;
            }
            else {
                _this.isShow = false;
            }
        });
    }
    ScrollTopComponent.prototype.backToTop = function () {
        window.scrollTo(0, 0);
    };
    ScrollTopComponent = __decorate([
        core_1.Component({
            selector: 'scroll-to-top',
            templateUrl: 'app/ScrollTopComponent/scrollTop.component.html',
            styleUrls: ['app/ScrollTopComponent/scrollTop.component.css'],
            providers: [{ provide: Window, useValue: window }]
        }), 
        __metadata('design:paramtypes', [Window, core_1.ElementRef])
    ], ScrollTopComponent);
    return ScrollTopComponent;
}());
exports.ScrollTopComponent = ScrollTopComponent;
//# sourceMappingURL=scrollTop.component.js.map