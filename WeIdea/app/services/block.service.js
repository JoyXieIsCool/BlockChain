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
var blocks = [{
        hash: '123',
        transctions: ['REQB_A_B_100_20150623080020111', 'RESB_B_A_100_20150623080020111'],
    }, {
        hash: '124',
        transctions: ['REQB_A_B_100_20150623080020111', 'RESB_B_A_100_20150623080020111'],
    }];
var BlockService = (function () {
    function BlockService(http) {
        this.http = http;
        this.blocks = blocks;
        this.url = 'http://localhost:8081/bc/queryBlock';
    }
    BlockService.prototype.get = function (cur) {
        return this.http.get(this.url + '?currentBlock=' + cur).map(function (r) { return r.json(); });
        // return Observable.from<Block[]>(this.blocks);
    };
    BlockService.prototype.post = function () {
    };
    BlockService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], BlockService);
    return BlockService;
}());
exports.BlockService = BlockService;
//# sourceMappingURL=block.service.js.map