import { bootstrap } from '@angular/platform-browser-dynamic';
import { AppComponent } from './AppComponent/app.component';
import { LoadingComponent } from './LoadingComponent/loading.component';
import { appRouterProviders } from './RoutesProviders/app.routes';
import { HTTP_PROVIDERS } from '@angular/http';

bootstrap(AppComponent, [appRouterProviders, HTTP_PROVIDERS]);