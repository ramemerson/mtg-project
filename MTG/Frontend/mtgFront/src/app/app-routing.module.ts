import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { AuthGuard } from './guards/auth.guard';
import { BrowseComponent } from './browse/browse.component';
import { TradeComponent } from './trade/trade.component';
import { AccountComponent } from './account/account.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'frontpage',
    component: FrontpageComponent,
    canActivate: [AuthGuard],
  },
  { path: 'browse', component: BrowseComponent, canActivate: [AuthGuard] },
  { path: 'trade', component: TradeComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
