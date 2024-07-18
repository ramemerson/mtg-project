import { importProvidersFrom, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { environment } from '../environments/environment';
import { AccountControllerClient, API_BASE_URL } from './services/mtg.service';
import { provideHttpClient } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { LoginComponent } from './login/login.component';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, ButtonModule, FormsModule],
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
