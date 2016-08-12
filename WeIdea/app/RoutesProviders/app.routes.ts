import { provideRouter, RouterConfig } from '@angular/router';
import { AppComponent } from '../AppComponent/app.component';
import { LoadingComponent } from '../LoadingComponent/loading.component';
import { DashboardComponent } from '../DashboardComponent/dashboard.component';

const routes: RouterConfig = [
  {
    path: '',
    redirectTo: '/index',
    pathMatch: 'full'
  },
  {
    path: 'index',
    component: LoadingComponent,
    name: 'Loading'
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    name: 'dashboard'
  }
];

export const appRouterProviders = [
  provideRouter(routes)
]