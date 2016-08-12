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
var RolesComponent = (function () {
    function RolesComponent() {
    }
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], RolesComponent.prototype, "mine", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], RolesComponent.prototype, "roles", void 0);
    RolesComponent = __decorate([
        core_1.Component({
            selector: 'role-list',
            templateUrl: 'app/RolesComponent/roles.component.html',
            styleUrls: ['app/RolesComponent/roles.component.css']
        }), 
        __metadata('design:paramtypes', [])
    ], RolesComponent);
    return RolesComponent;
}());
exports.RolesComponent = RolesComponent;
//# sourceMappingURL=roles.component.js.map