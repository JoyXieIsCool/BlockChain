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
var Instruct2Action = (function () {
    function Instruct2Action() {
    }
    Instruct2Action.prototype.transform = function (value) {
        var map = {
            '1000': '借给',
            '1001': '收到',
            '1002': '还给'
        };
        return !!value ? map[value] : '';
    };
    Instruct2Action = __decorate([
        core_1.Pipe({
            name: 'instruct2Action'
        }), 
        __metadata('design:paramtypes', [])
    ], Instruct2Action);
    return Instruct2Action;
}());
exports.Instruct2Action = Instruct2Action;
//# sourceMappingURL=instruct2Action.pipe.js.map