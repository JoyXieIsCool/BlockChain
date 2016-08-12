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
var State2Text = (function () {
    function State2Text() {
    }
    State2Text.prototype.transform = function (value, act) {
        // console.log(act)
        var action = act == 1 ? '借' : '还';
        var map = ['有借有还，再借不难！', '请选择操作！', (action + "\u7ED9\u8C01\uFF1F"), '请输入金额！', '', '操作完毕！'];
        return value >= 0 && value < map.length ? map[value] : "Error!";
    };
    State2Text = __decorate([
        core_1.Pipe({
            name: 'state2Text'
        }), 
        __metadata('design:paramtypes', [])
    ], State2Text);
    return State2Text;
}());
exports.State2Text = State2Text;
//# sourceMappingURL=state2Text.pipe.js.map