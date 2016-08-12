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
var block_1 = require('../data/block');
var BlockComponent = (function () {
    function BlockComponent() {
    }
    BlockComponent.prototype.constrcutor = function () { };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', block_1.Block)
    ], BlockComponent.prototype, "block", void 0);
    BlockComponent = __decorate([
        core_1.Component({
            selector: 'block-detail',
            templateUrl: 'app/BlockComponent/block.component.html',
            styleUrls: ['app/BlockComponent/block.component.css']
        }), 
        __metadata('design:paramtypes', [])
    ], BlockComponent);
    return BlockComponent;
}());
exports.BlockComponent = BlockComponent;
//# sourceMappingURL=block.component.js.map