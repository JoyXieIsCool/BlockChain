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
var http_1 = require('@angular/http');
require('rxjs/add/observable/of');
require('rxjs/add/observable/from');
require('rxjs/add/operator/map');
var NotifyService = (function () {
    function NotifyService(http) {
        this.http = http;
        this.url = 'http://localhost:8081/bc/queryResponse';
    }
    //查看是否有新数据
    NotifyService.prototype.get = function () {
        return this.http.get(this.url).map(function (r) { return r.json(); });
    };
    //返回用户选择
    NotifyService.prototype.post = function (act) {
        return this.http.post(this.url, { isAck: (act ? 'Y' : 'N') }).map(function (r) { return r.json(); });
    };
    NotifyService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], NotifyService);
    return NotifyService;
}());
exports.NotifyService = NotifyService;
//# sourceMappingURL=notify.service.js.map