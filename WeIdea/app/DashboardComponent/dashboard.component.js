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
var role_service_1 = require('../services/role.service');
var notify_service_1 = require('../services/notify.service');
var displayZone_component_1 = require('../DisplayZoneComponent/displayZone.component');
var roles_component_1 = require('../RolesComponent/roles.component');
var terminal_component_1 = require('../TerminalComponent/terminal.component');
var scrollTop_component_1 = require('../ScrollTopComponent/scrollTop.component');
var cdate_pipe_1 = require('../pipes/cdate.pipe');
var instruct2Action_pipe_1 = require('../pipes/instruct2Action.pipe');
console.log(notify_service_1.NotifyService);
var DashboardComponent = (function () {
    function DashboardComponent(blockService, roleService, notifyService) {
        this.blockService = blockService;
        this.roleService = roleService;
        this.notifyService = notifyService;
        this.needComfirm = false;
        this.message = [];
        this.during = 1000;
    }
    DashboardComponent.prototype.ngOnInit = function () {
        this.updateRoles();
        this.getMessage();
    };
    DashboardComponent.prototype.ngOnDestroy = function () {
    };
    DashboardComponent.prototype.updateRoles = function () {
        var that = this;
        this.roleService.get().subscribe(function (data) {
            that.roles = data.nodes;
            that.mine = data.me;
        });
        if (!!this.timer) {
            clearTimeout(this.timer);
        }
        this.timer = setTimeout(function () {
            that.updateRoles();
        }, this.during * 2);
    };
    DashboardComponent.prototype.getMessage = function () {
        var that = this;
        this.notifyService.get().subscribe({
            next: function (value) {
                console.log(value);
                if (!!value["alert"] && value["alert"] == '1') {
                    that.needComfirm = true;
                    var tmp = value.msg.split('_');
                    var instruct = tmp[0], from = tmp[1], to = tmp[2], amount = tmp[3], date = tmp[4];
                    var o = {
                        instruct: instruct, from: from, to: to, date: date,
                        amount: +amount
                    };
                    that.message = o;
                }
            }
        });
        if (!!this.messageTimer) {
            clearTimeout(this.messageTimer);
        }
        this.messageTimer = setTimeout(function () { that.getMessage(); }, 1000);
    };
    DashboardComponent.prototype.featBack = function (act) {
        var that = this;
        return that.notifyService.post(act).subscribe({
            next: function (value) {
                data.status == '1' ? alert('操作成功！') : alert('操作失败！');
                that.needComfirm = false;
            }
        });
    };
    DashboardComponent = __decorate([
        core_1.Component({
            selector: 'dashborad',
            templateUrl: 'app/DashboardComponent/dashboard.component.html',
            styleUrls: ['app/DashboardComponent/dashboard.component.css'],
            providers: [block_service_1.BlockService, role_service_1.RoleService, notify_service_1.NotifyService],
            directives: [displayZone_component_1.DisplayZoneComponent, roles_component_1.RolesComponent, terminal_component_1.TerminalComponent, scrollTop_component_1.ScrollTopComponent],
            pipes: [cdate_pipe_1.CdatePipe, instruct2Action_pipe_1.Instruct2Action]
        }), 
        __metadata('design:paramtypes', [block_service_1.BlockService, role_service_1.RoleService, notify_service_1.NotifyService])
    ], DashboardComponent);
    return DashboardComponent;
}());
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map