import { importProvidersFrom, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { environment } from '../environments/environment';
import { AccountControllerClient, API_BASE_URL } from './services/mtg.service';
import { provideHttpClient } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponentComponent } from './login-component/login-component.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponentComponent],
  imports: [BrowserModule, AppRoutingModule],
  providers: [
    provideHttpClient(),
    importProvidersFrom(CommonModule, BrowserModule, AccountControllerClient),
    {
      provide: API_BASE_URL,
      useFactory: () => environment.apiUrl,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
