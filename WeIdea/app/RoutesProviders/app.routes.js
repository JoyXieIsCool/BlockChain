"use strict";
var router_1 = require('@angular/router');
var loading_component_1 = require('../LoadingComponent/loading.component');
var dashboard_component_1 = require('../DashboardComponent/dashboard.component');
var routes = [
    {
        path: '',
        redirectTo: '/index',
        pathMatch: 'full'
    },
    {
        path: 'index',
        component: loading_component_1.LoadingComponent,
        name: 'Loading'
    },
    {
        path: 'dashboard',
        component: dashboard_component_1.DashboardComponent,
        name: 'dashboard'
    }
];
exports.appRouterProviders = [
    router_1.provideRouter(routes)
];
//# sourceMappingURL=app.routes.js.map