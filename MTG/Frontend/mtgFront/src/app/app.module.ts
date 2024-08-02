import { importProvidersFrom, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
  provideHttpClient,
  withFetch,
  withInterceptors,
} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { environment } from '../environments/environment';
import { AccountControllerClient, API_BASE_URL } from './services/mtg.service';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { CalendarModule } from 'primeng/calendar';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { BrowseComponent } from './browse/browse.component';
import { TradeComponent } from './trade/trade.component';
import { AccountComponent } from './account/account.component';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { ImageModule } from 'primeng/image';
import { DataViewModule } from 'primeng/dataview';
import { ProgressBarModule } from 'primeng/progressbar';
import { DialogModule } from 'primeng/dialog';
import { EditComponent } from './account/edit/edit.component';
import { CacheService } from './services/cache/cache.service';
import { cachingInterceptor } from './http-interceptors/http-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    FrontpageComponent,
    BrowseComponent,
    TradeComponent,
    AccountComponent,
    EditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    FormsModule,
    InputTextModule,
    PasswordModule,
    MessagesModule,
    MessageModule,
    CalendarModule,
    ReactiveFormsModule,
    CardModule,
    DividerModule,
    ImageModule,
    DataViewModule,
    ProgressBarModule,
    DialogModule,
  ],
  providers: [
    CacheService,
    provideHttpClient(withFetch(), withInterceptors([cachingInterceptor])),
    importProvidersFrom(CommonModule, BrowserModule, AccountControllerClient),
    {
      provide: API_BASE_URL,
      useFactory: () => environment.apiUrl,
    },
    provideAnimationsAsync(),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
