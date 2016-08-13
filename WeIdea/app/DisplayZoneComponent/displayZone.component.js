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
var block_service_1 = require('../services/block.service');
var cdate_pipe_1 = require('../pipes/cdate.pipe');
var instruct2Action_pipe_1 = require('../pipes/instruct2Action.pipe');
var DisplayZoneComponent = (function () {
    function DisplayZoneComponent(blockService) {
        this.blockService = blockService;
        this.blocks = [];
        this.blocksReverse = [];
        this.oldGuy = 0;
        this.newGuy = 0;
    }
    DisplayZoneComponent.prototype.ngOnInit = function () {
        this.updateBlocks();
        // setTimeout(()=>{
        //   this.blocks.unshift({
        //       hash: '0000',  
        //       transctions: ['REQB_A_B_100_20150623080020111','RESB_B_A_100_20150623080020111'],
        //     });
        //   console.log(blocks)
        // }, 1000)
    };
    DisplayZoneComponent.prototype.ngOnDestroy = function () {
        if (!!this.timer) {
            clearTimeout(this.timer);
        }
    };
    DisplayZoneComponent.prototype.updateBlocks = function () {
        var that = this;
        var counter = 0;
        this.oldGuy = this.blocks.length;
        this.blockService
            .get(that.blocks.length)
            .subscribe({
            next: function (value) {
                //查找数据，找不到就push进数组,
                //后台查找了，直接push进去就好
                console.log(value);
                console.log(Array.isArray(value.transctions));
                if (Array.isArray(value)) {
                    value.forEach(function (cur) {
                        cur.ttransctions = cur.content.map(function (transction) {
                            var tmp = transction.split('_');
                            var instruct = tmp[0], from = tmp[1], to = tmp[2], amount = tmp[3], date = tmp[4];
                            var o = {
                                instruct: instruct, from: from, to: to, date: date,
                                amount: +amount
                            };
                            return o;
                        });
                        counter++;
                        //倒着插入
                        // console.log(cur);
                        // console.log(counter);
                        that.blocks.push(cur);
                        // console.log(that.blocks);           
                    });
                }
                console.log(that.blocks);
            },
            complete: function () {
                if (counter > 0) {
                    that.newGuy = counter;
                }
            }
        });
        if (!!that.timer) {
            clearTimeout(that.timer);
        }
        that.timer = setTimeout(function () {
            that.updateBlocks();
        }, 1000);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], DisplayZoneComponent.prototype, "mine", void 0);
    DisplayZoneComponent = __decorate([
        core_1.Component({
            selector: 'display-zone',
            templateUrl: 'app/DisplayZoneComponent/displayZone.component.html',
            styleUrls: ['app/DisplayZoneComponent/displayZone.component.css'],
            pipes: [cdate_pipe_1.CdatePipe, instruct2Action_pipe_1.Instruct2Action],
            animations: [
                core_1.trigger('colorRun', [
                    core_1.transition('void => *', [
                        core_1.animate('1s 1s ease-in-out', core_1.keyframes([
                            core_1.style({ 'width': '0', offset: 0 }),
                            core_1.style({ 'width': '100%', offset: 1 })
                        ]))
                    ])
                ])
            ]
        }), 
        __metadata('design:paramtypes', [block_service_1.BlockService])
    ], DisplayZoneComponent);
    return DisplayZoneComponent;
}());
exports.DisplayZoneComponent = DisplayZoneComponent;
//# sourceMappingURL=displayZone.component.js.map