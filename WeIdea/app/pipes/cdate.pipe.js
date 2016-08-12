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
var CdatePipe = (function () {
    function CdatePipe() {
    }
    CdatePipe.prototype.transform = function (value) {
        console.log(value);
        var formatDate = 'yyyy/MM/dd hh:mm';
        var date_arr = /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})(\d{3})/.exec(value);
        if (date_arr) {
            formatDate = date_arr[1] + '/' + date_arr[2] + '/' + date_arr[3] + ' ' +
                date_arr[4] + ':' + date_arr[5] + ':' + date_arr[6] + ':' + date_arr[7];
        }
        return formatDate;
    };
    CdatePipe = __decorate([
        core_1.Pipe({
            name: 'cdate'
        }), 
        __metadata('design:paramtypes', [])
    ], CdatePipe);
    return CdatePipe;
}());
exports.CdatePipe = CdatePipe;
//# sourceMappingURL=cdate.pipe.js.map