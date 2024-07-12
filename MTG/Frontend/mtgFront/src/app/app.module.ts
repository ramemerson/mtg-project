import { importProvidersFrom, NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { environment } from '../environments/environment';
import { API_BASE_URL } from './services/mtg.service';
import { LoginComponentComponent } from './login-component/login-component.component';

@NgModule({
  declarations: [AppComponent, LoginComponentComponent],
  imports: [BrowserModule, AppRoutingModule],
  providers: [
    importProvidersFrom(CommonModule, BrowserModule),
    {
      provide: API_BASE_URL,
      useFactory: () => environment.apiUrl,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
