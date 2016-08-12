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
var state2Text_pipe_1 = require('../pipes/state2Text.pipe');
var instruct_service_1 = require('../services/instruct.service');
var TerminalComponent = (function () {
    function TerminalComponent(instructService) {
        this.instructService = instructService;
        this.state = 0;
        this.act = 1;
    }
    TerminalComponent.prototype.getStart = function () {
        this.state = 1;
    };
    TerminalComponent.prototype.selectAction = function (act) {
        this.act = act;
        if (act === 1) {
            this.action = "1000";
        }
        else {
            this.action = "1002";
        }
        //进入下一个状态；
        this.state = 2;
    };
    TerminalComponent.prototype.selectTarget = function () {
        //进入下一个状态；
        if (!this.target)
            return;
        this.state = 3;
    };
    TerminalComponent.prototype.comfirm = function () {
        var _this = this;
        //提交
        if (!this.amount)
            return;
        this.state = 4;
        var str = this.action + '_' + this.mine + '_' + this.target + '_' + this.amount;
        this.instructService.post(str).subscribe(function (data) {
            _this.isSuccess = data.status == '1' ? true : false;
            _this.state = 5;
        });
    };
    TerminalComponent.prototype.reset = function () {
        //重置
        this.state = 0;
        this.amount = undefined;
        this.target = undefined;
        this.act = 1;
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], TerminalComponent.prototype, "roles", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], TerminalComponent.prototype, "mine", void 0);
    TerminalComponent = __decorate([
        core_1.Component({
            selector: 'terminal-board',
            templateUrl: 'app/TerminalComponent/terminal.component.html',
            styleUrls: ['app/TerminalComponent/terminal.component.css'],
            pipes: [state2Text_pipe_1.State2Text],
            providers: [instruct_service_1.InstructService],
            animations: [
                core_1.trigger('fadeOut', [
                    core_1.transition('*=>void', core_1.animate('.3s .3s easeInOut', core_1.keyframes([
                        core_1.style({ offset: 0, opacity: 1 }),
                        core_1.style({ offset: 1, opacity: 0 })
                    ])))
                ])
            ]
        }), 
        __metadata('design:paramtypes', [instruct_service_1.InstructService])
    ], TerminalComponent);
    return TerminalComponent;
}());
exports.TerminalComponent = TerminalComponent;
//# sourceMappingURL=terminal.component.js.map